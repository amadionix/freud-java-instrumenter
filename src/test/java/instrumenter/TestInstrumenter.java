package instrumenter;

import org.junit.Test;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.assertTrue;

public class TestInstrumenter {

    @Test
    public void testSimpleMEthod() throws IOException, InterruptedException {
        System.out.println("TESTING SIMPLE METHOD\n----------------------------------------------------------------------");
        Process proc = Runtime.getRuntime().exec("java -cp target/freud-java-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar:target/classes:target/test-classes instrumenter.InstrumentMethod demo.classes.TestSimpleMethod simpleMethod");
        proc.waitFor();
        // Then retreive the process output
        InputStream in = proc.getInputStream();
        InputStream err = proc.getErrorStream();

        byte[] b =new byte[in.available()];
        in.read(b,0,b.length);
        System.out.println(new String(b));

        byte[] c =new byte[err.available()];
        err.read(c,0,c.length);
        System.out.println(new String(c));

        proc = Runtime.getRuntime().exec("mkdir sootOutput/instrumenter");
        proc.waitFor();
        proc = Runtime.getRuntime().exec("cp target/classes/instrumenter/MyTimer.class sootOutput/instrumenter/");
        proc.waitFor();

        proc = Runtime.getRuntime().exec("java -cp sootOutput demo.classes.TestSimpleMethod");
        proc.waitFor();
        // Then retreive the process output
        in = proc.getInputStream();
        err = proc.getErrorStream();

        b =new byte[in.available()];
        in.read(b,0,b.length);
        System.out.println(new String(b));

        c =new byte[err.available()];
        err.read(c,0,c.length);
        System.out.println(new String(c));

        assertTrue((new String(c)).isEmpty());
    }

    @Test
    public void testMethodWithTryCatch() throws IOException, InterruptedException {
        System.out.println("TESTING METHOD WITH TRY-CATCH\n----------------------------------------------------------------------");
        Process proc = Runtime.getRuntime().exec("java -cp target/freud-java-instrumentation-1.0-SNAPSHOT-jar-with-dependencies.jar:target/classes:target/test-classes instrumenter.InstrumentMethod demo.classes.TestMethodWithTryCatch methodWithTryCatch");
        proc.waitFor();
        // Then retreive the process output
        InputStream in = proc.getInputStream();
        InputStream err = proc.getErrorStream();

        byte[] b =new byte[in.available()];
        in.read(b,0,b.length);
        System.out.println(new String(b));

        byte[] c =new byte[err.available()];
        err.read(c,0,c.length);
        System.out.println(new String(c));

        proc = Runtime.getRuntime().exec("cp target/classes/instrumenter/MyTimer.class sootOutput/instrumenter/");
        proc.waitFor();

        proc = Runtime.getRuntime().exec("java -cp sootOutput demo.classes.TestMethodWithTryCatch");
        proc.waitFor();
        // Then retreive the process output
        in = proc.getInputStream();
        err = proc.getErrorStream();

        b =new byte[in.available()];
        in.read(b,0,b.length);
        System.out.println(new String(b));

        c =new byte[err.available()];
        err.read(c,0,c.length);
        System.out.println(new String(c));

        assertTrue((new String(c)).isEmpty());
    }

}
