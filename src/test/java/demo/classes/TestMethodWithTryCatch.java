package demo.classes;

public class TestMethodWithTryCatch {

    public static void main(String[] args) {
        methodWithTryCatch();
    }

    public static void methodWithTryCatch() {
        System.out.println("You are inside methodWithTryCatch()");
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
