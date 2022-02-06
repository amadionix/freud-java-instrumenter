package demo.classes;

public class MicroBenchmark {

    public static void main(String[] args) throws InterruptedException {
        testQuad(Integer.parseInt(args[0]));
    }

    /* numeric */

    private static void testLinear(int t) throws InterruptedException {
        System.out.println(t);
        for (int i = 0; i < t; i++) {
            Thread.sleep(250);
        }
    }

    private static void testQuad(int t) throws InterruptedException {
        for (int i = 0; i < t; i++) {
            for (int j = 0; j < t; j++) {
                Thread.sleep(250);
            }
        }
    }

    /* strings */

    private static void testLinearStr(String str) throws InterruptedException {
        for (int i = 0; i < str.length(); i++) {
            Thread.sleep(100);
        }
    }


}
