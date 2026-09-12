package book.java_cc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.IntStream;


public class FuterTaskDemo {
    static void main(String[] args) throws Exception {
        var executor = Executors.newCachedThreadPool();
        get10Task(executor);
        executor.shutdown();
    }

    public static void get10Task(ExecutorService executor) throws InterruptedException, ExecutionException{
        List<Future<Integer>> tasks = new ArrayList<>();
        IntStream.range(1,10).forEach(i -> {
            Future<Integer> future = executor.submit(() -> {
                Thread.sleep(10_000);//work
                return i;
            });
            tasks.add(future);
        });
        for (Future<Integer> r : tasks) {
            System.out.println(r.get());
        }
    }
}
