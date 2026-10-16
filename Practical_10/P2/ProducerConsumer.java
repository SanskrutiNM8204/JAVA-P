package Practical_10.P2;
import java.util.*;
public class ProducerConsumer {
    static Queue<Integer> queue = new LinkedList<>();
    static int SIZE = 5;
    public static void main(String[] args) throws Exception {
        Thread producer = new Thread(() -> {
            for (int i = 1; i <= SIZE; i++) {
                synchronized (queue) {
                    queue.add(i);
                    System.out.println("Produced: " + i);
                    queue.notify();
                }
            }
        });
        Thread consumer = new Thread(() -> {
            for (int i = 1; i <= SIZE; i++) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        try { queue.wait(); }
                        catch (InterruptedException e) { Thread.currentThread().interrupt(); return; }
                    }
                    int value = queue.remove();
                    System.out.println("Consumed: " + value);
                }
            }
        });
        consumer.start();
        producer.start();
        producer.join();
        consumer.join();
    }
}
