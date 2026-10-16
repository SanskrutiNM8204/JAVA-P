package Practical_10.P1;
import java.util.concurrent.*;
public class ThreadPoolRunner {
    public static void main(String[] args) throws Exception {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        for (int i = 1; i <= 10; i++) {
            int id = i;
            pool.submit(() -> {
                System.out.println("Task " + id + " running on " + Thread.currentThread().getName());
            });
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        System.out.println("All tasks completed");
    }
}
