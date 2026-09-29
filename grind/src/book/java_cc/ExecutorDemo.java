package book.java_cc;

import java.util.concurrent.*;

public class ExecutorDemo {


    static void main() {
        ThreadPoolExecutor executor
                = new ThreadPoolExecutor(8, 8,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(8));
        executor.setRejectedExecutionHandler(
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
