package book;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class Latch {
    private final static ExecutorService ex = Executors.newFixedThreadPool(2);
    private final static CountDownLatch latch = new CountDownLatch(2);

    static void main() {
        ex.execute(() -> {
            try {
                System.out.println("t1 -1");
                latch.countDown();

                latch.await();
                System.out.println("start 1");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        ex.execute(() -> {
            try {
                sleep(2000);

                System.out.println("t2 -2");
                latch.countDown();
                latch.await();
                System.out.println("start 2");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
