package book.java_cc.thread_pool;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

public class ExecutorDemo {

    static void main() {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(8, 8, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>(8));
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        for (int i = 0; i < 8 * 2; i++) {
            final int ii = i;
            executor.submit(() -> {
                try {
                    sleep(10_000);
                    System.out.println(ii);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            });
        }
        executor.submit(() -> {
            System.out.println("Start");
            System.out.println("End");
        });

    }
}
