package workload.generator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Generator {
    /*
    working with the assumptions that the analyzed methods only have
    1 parameter, of type <int>
     */
    private static String PATHNAME = "param-logs.txt";

    public static void main(String[] args) throws IOException {
        Runtime.getRuntime().exec("rm param-logs.txt");
        File file = new File(PATHNAME);
        file.createNewFile();

        FileWriter writer = new FileWriter(PATHNAME, true);
        int samplesCount = 30;
        writer.write(samplesCount + "\n");
        for (int i = 1; i <= samplesCount; i++) {
            writer.write(i + "\n");
        }
        writer.close();

    }
}
