package book.java_cc;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.Executors;

import static java.lang.Thread.sleep;

public class ExecutorCompletionServiceDemo {

    private static final ExecutorCompletionService SERVICE = new ExecutorCompletionService<>(Executors.newCachedThreadPool());

    static void main() throws InterruptedException, ExecutionException {
        do10();
        for (int i = 0; i < 15; i++) {
            var future = SERVICE.take();
            System.out.println(future.get());
        }
    }

    private static void do10() {
        for (int i = 0; i < 5; i++) {
            Callable<Double> task = new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return doTask(3_000);
                }
            };
            SERVICE.submit(task);
        }
        for (int i = 0; i < 5; i++) {
            Callable<Double> task = new Callable<Double>() {
                @Override
                public Double call() throws Exception {
                    return doTask(5_000);
                }
            };
            SERVICE.submit(task);
        }
    }

    private static double doTask(int millis) throws InterruptedException {
        sleep(millis);
        return Math.random();
    }

}
