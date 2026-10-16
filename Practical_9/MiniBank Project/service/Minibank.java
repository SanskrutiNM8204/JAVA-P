package service;

public class MiniBank {

    public static void main(String[] args) throws Exception {

        Account account = new Account(0);

        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {

            threads[i] = new Thread(
                new AccountWorker(account, 1000, 1),
                "Thread-" + i
            );

            threads[i].start();
        }

        for (Thread t : threads) {
            t.join();
        }

        System.out.println("Final balance: " + account.getBalance());
    }
}