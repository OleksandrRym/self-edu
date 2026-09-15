package book.java_cc;

import java.util.List;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class CyclicBarrierDemo {
    private final static CyclicBarrier CYCLIC_BARRIER  =new CyclicBarrier(2,()->{
        System.out.println("start fight");
    });

    static ExecutorService pool = Executors.newCachedThreadPool();
    private static final List<String> names = List.of("Oleg", "Alex");

    static void main() throws InterruptedException {
       pool.execute(()->{
           try {
               System.out.println("Ready " + names.get(0));
               sleep(3000);
               CYCLIC_BARRIER.await();
               System.out.println("start" + names.get(0));
           } catch (InterruptedException | BrokenBarrierException e) {
               throw new RuntimeException(e);
           }
       });
        pool.execute(()->{
            try {
                System.out.println("Ready " + names.get(1));
                sleep(3000);
                CYCLIC_BARRIER.await();
                System.out.println("start" + names.get(1));
            } catch (InterruptedException | BrokenBarrierException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
