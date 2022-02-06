package instrumenter;

import soot.*;
import soot.jimple.*;
import soot.options.Options;
import soot.util.*;

import java.io.File;
import java.util.*;

public class InvokeStaticInstrumenter extends BodyTransformer{

    static SootClass timerClass;
    static SootMethod startTiming, reportDuration;
    static {
        String sourceDirectory = System.getProperty("user.dir") +
                File.separator + "target" + File.separator + "classes";
        Options.v().set_soot_classpath(sourceDirectory);
        timerClass = Scene.v().loadClassAndSupport("instrumenter.MyTimer");
        startTiming = timerClass.getMethod("void startTiming()");
        reportDuration = timerClass.getMethod("void reportDuration()");
    }
    final String targetMethod;

    public InvokeStaticInstrumenter(String targetMethod) {
        this.targetMethod = targetMethod;
    }

    /* internalTransform goes through a method body and inserts */
    protected void internalTransform(Body body, String phase, Map options) {
        SootMethod method = body.getMethod();
        if (method.getName().equals(targetMethod)) {
            System.out.println("instrumenting method : " + method.getSignature());
            InvokeExpr incExpr = Jimple.v().newStaticInvokeExpr(startTiming.makeRef());
            Stmt incStmt = Jimple.v().newInvokeStmt(incExpr);
            Chain units = body.getUnits();

            //  insert new statement into the chain (we are mutating the unit chain).
            units.insertBefore(incStmt, ((JimpleBody) body).getFirstNonIdentityStmt());

            Iterator stmtIt = units.snapshotIterator();
            Stmt stmt;
            while (stmtIt.hasNext()) {
                stmt = (Stmt) stmtIt.next();
                if ((stmt instanceof ReturnStmt) || (stmt instanceof ReturnVoidStmt)) {
                    InvokeExpr reportExpr = Jimple.v().newStaticInvokeExpr(reportDuration.makeRef());
                    Stmt reportStmt = Jimple.v().newInvokeStmt(reportExpr);
                    units.insertBefore(reportStmt, stmt);
                }
            }
        }
    }
}
