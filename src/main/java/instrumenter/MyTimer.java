package instrumenter;

import java.io.*;

public class MyTimer {
    private static long time;
    private static String PATHNAME = "time-logs.txt";
    private static FileWriter writer;

    public MyTimer() throws IOException {
        File out = new File(PATHNAME);
        out.createNewFile();
    }

    public static synchronized void startTiming() {
        time = System.currentTimeMillis();
    }

    public static synchronized void reportDuration() throws IOException {
        writer = new FileWriter(PATHNAME, true);
        writer.write((System.currentTimeMillis() - time) + "\n");
        writer.close();
        System.out.println("You have stayed " +
                (System.currentTimeMillis() - time) + " milliseconds in " +
                "your target method.");
    }

}
