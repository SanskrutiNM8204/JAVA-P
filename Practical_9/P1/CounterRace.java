package Practical_9.P1;
public class CounterRace {
    static int count = 0;
    static void increment() { count++; }
    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) increment();
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Final count: " + count);
    }
}
