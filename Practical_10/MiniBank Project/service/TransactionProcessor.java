import java.util.concurrent.*;
public class TransactionProcessor {
    private final ExecutorService pool = Executors.newFixedThreadPool(4);
    public void submit(Runnable task) { pool.execute(task); }
    public void stop() throws InterruptedException {
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
    }
    public static void main(String[] args) throws Exception {
        TransactionProcessor processor = new TransactionProcessor();
        for (int i = 1; i <= 5; i++) {
            int id = i;
            processor.submit(() ->
                System.out.println("Transaction " + id + " processed by " +
                Thread.currentThread().getName()));
        }
        processor.stop();
        System.out.println("Processor stopped");
    }
}
