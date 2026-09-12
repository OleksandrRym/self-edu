package book.java_cc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class SemaphoreDemo {
    static Semaphore semaphore = new Semaphore(2);
    static ExecutorService pool = Executors.newCachedThreadPool();
    private static final List<String> names = List.of("Oleg", "Alex", "Sasha", "Stepan", "Stefan");

    static void main() throws InterruptedException, ExecutionException {
        List<Future<String>> tasks = new ArrayList();
        var emails = new ArrayList<>();

        names.forEach(name -> {
            Future<String> f = pool.submit(() -> {
                return getInLimitedResource(name);
            });
            tasks.add(f);
        });
       tasks.forEach(el -> {
           try {
               String email = el.get();
               emails.add(email);
           } catch (ExecutionException | InterruptedException e) {
               throw new RuntimeException(e);
           }
       });

        System.out.println(emails);
    }

    public static String getInLimitedResource(String username) throws InterruptedException {
        try  {
            semaphore.acquire();
            System.out.println("entered " + username);
            return getEmailByUsername(username);
        } finally {
            semaphore.release();
        }
    }

    private static String getEmailByUsername(String username) throws InterruptedException {
        var s = new StringBuilder();
        s.append(username);
        s.append("@gmail.com");
        Thread.sleep(3000);//do...
        return s.toString();
    }
}
