package demo.classes;

public class TestSimpleMethod {

    public static void main(String[] args) throws InterruptedException {
        simpleMethod();
    }

    private static void simpleMethod() throws InterruptedException {
        System.out.println("You are inside simpleMethod()");
        Thread.sleep(200);
    }

}
