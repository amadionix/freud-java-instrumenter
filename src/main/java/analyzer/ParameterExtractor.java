package analyzer;

import soot.*;
import soot.jimple.JimpleBody;
import soot.options.Options;
import soot.util.Chain;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ParameterExtractor {

    public static String clsName;
    public static String methodName;
    public static String sourceDirectory = System.getProperty("user.dir") + "/target/test-classes/demo/classes";

    public static void setupSoot() {
        G.reset();
        Options.v().set_prepend_classpath(true);
        Options.v().set_allow_phantom_refs(true);
        Options.v().set_soot_classpath(sourceDirectory);
        SootClass sc = Scene.v().loadClassAndSupport(clsName);
        sc.setApplicationClass();
        Scene.v().loadNecessaryClasses();
    }

    public static void main(String[] args) {
        if (args.length < 2){
            System.err.println(
                    "Please provide both the name of the Java class and " +
                            "of the method that you want to instrument.");
            return;
        }
        clsName = args[0];
        methodName = args[1];
        if (!isClsNameValid()) return;
        setupSoot();
        SootClass mainClass = Scene.v().getSootClass(clsName);
        SootMethod sm = getSootMethod(mainClass);
        JimpleBody body = (JimpleBody) sm.retrieveActiveBody();

        System.out.format("Method %s of class %s:%n", methodName, clsName);
        System.out.println("Argument(s):");
        for (Local l : body.getParameterLocals()) {
            System.out.println(l.getName() + " : " + l.getType());
        }
    }

    private static boolean isClsNameValid() {
        Path classPath = Paths.get(sourceDirectory + File.separator + clsName + ".class");
        if (!Files.exists(classPath)) {
            System.err.println("Couldn't find " + clsName + " class. Make sure you did the following steps:\n" +
                    "(1) add " + clsName + " source code to the package demo.classes;\n" +
                    "(2) build the project;\n" +
                    "(3) run extractor.ParameterExtractor.");
            return false;
        }
        return true;
    }

    private static SootMethod getSootMethod(SootClass mainClass) {
        SootMethod sm;
        try {
            sm = mainClass.getMethodByName(methodName);
        } catch (soot.AmbiguousMethodException e) {
            System.out.println("Ambiguous method name, please specify entire signature, like for example <int countChar(String)>.");
            String signature = System.console().readLine();
            sm = mainClass.getMethod(signature);
        }
        return sm;
    }

}
