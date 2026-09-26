package book.java_cc;

import static java.lang.Thread.sleep;

public class HookDemo {
    static void main() throws InterruptedException {

        addHook();
        sleep(3_000);
        gracefulShutdown();
    }

    static void gracefulShutdown() {
        System.out.println("before shutdown");
        System.exit(202);
        System.out.println("after shutdown");//never printer
    }

    static void forceShutdown() {
        System.out.println("before shutdown");
        Runtime.getRuntime().halt(202);
        //Hook never printed
        System.out.println("after shutdown");//never printer
    }

    private static void addHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.out.println("END");
        }));
    }
}
