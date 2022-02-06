package instrumenter;

import soot.*;
import soot.jimple.JimpleBody;
import soot.options.Options;

public class InstrumentMethod {

    public static void main(String[] args) {
        if (args.length == 0) {
           System.err.println("Usage: java instrumenter.InstrumentMethod [options] classname");
           System.exit(0);
        }

        // add the path to your rt.jar for your machine
        String pathToRtJar = "/Library/Java/JavaVirtualMachines/jdk1.8.0_321.jdk/Contents/Home/jre/lib/rt.jar";
        // TODO: this shouldn't be hardcoded, maybe it could be a parameter in a config file

        Scene.v().setSootClassPath(pathToRtJar
                + ":" + System.getProperty("user.dir") + "/target/test-classes"
                + ":" + System.getProperty("user.dir") + "/target/classes");

        /* add a phase to transformer pack by call Pack.add */
        Pack jtp = PackManager.v().getPack("jtp");
        jtp.add(new Transform("jtp.instrumenter",
                new InvokeStaticInstrumenter(args[1])));

        /* Give control to Soot to process all options,
        * instrumenter.InvokeStaticInstrumenter.internalTransform will get called.
        */
        soot.Main.main(new String[]{args[0]});
    }

}