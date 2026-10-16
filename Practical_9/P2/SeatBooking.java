package Practical_9.P2;
public class SeatBooking {
    static int seatsLeft = 5;
    static synchronized boolean bookSeat() {
        if (seatsLeft > 0) {
            seatsLeft--;
            return true;
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        Thread[] threads = new Thread[10];
        int[] booked = {0};
        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                if (bookSeat()) {
                    synchronized (booked) {
                        booked[0]++;
                    }
                }
            });
            threads[i].start();
        }
        for (Thread t : threads) t.join();
        System.out.println("Booked seats: " + booked[0]);
        System.out.println("Seats left: " + seatsLeft);
    }
}
