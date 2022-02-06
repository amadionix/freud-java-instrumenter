package instrumenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RunInstrumentedMethod {

    public static void main(String[] args) throws IOException, InterruptedException {
        String className = args[0];
        Runtime.getRuntime().exec("rm time-logs.txt");
        Process proc = Runtime.getRuntime().exec("cp target/classes/instrumenter/MyTimer.class sootOutput/instrumenter/");
        proc.waitFor();

        BufferedReader reader = new BufferedReader(new FileReader(
                System.getProperty("user.dir") + "/param-logs.txt"));
        String featuresCount = reader.readLine();
        String feature = reader.readLine();
        int i = 0;
        while (feature != null) {
            proc = Runtime.getRuntime().exec("java -cp sootOutput demo.classes." + className + " " + feature);
            proc.waitFor();
            feature = reader.readLine();
            i++;
        }
        assertEquals(i, Integer.parseInt(featuresCount));
        reader.close();
    }

}
