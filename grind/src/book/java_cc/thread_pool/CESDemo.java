package book.java_cc.thread_pool;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class CESDemo {
    private static final TimingThreadPool TIMING_THREAD_POOL = new TimingThreadPool(8, 8, 0L, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<>(8));

    static void main() {
        TIMING_THREAD_POOL.execute(() -> {
            System.out.println("run");
        });
        TIMING_THREAD_POOL.terminated();
    }
}
