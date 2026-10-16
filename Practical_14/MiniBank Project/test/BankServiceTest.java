package test;

import model.Account;

public class BankServiceTest {
    public static void main(String[] args) throws Exception {
        BankServiceTest test = new BankServiceTest();
        test.depositIncreasesBalance();
        test.withdrawalOverLimitThrows();
        test.concurrentDeposits();
        System.out.println("All BankService tests passed.");
    }

    public void depositIncreasesBalance() {
        Account account = new Account("AC1", "Riya", 1000);
        account.deposit(500);

        if (account.getBalance() != 1500L) {
            throw new AssertionError("Expected balance 1500 but got " + account.getBalance());
        }
    }

    public void withdrawalOverLimitThrows() {
        Account account = new Account("AC1", "Riya", 1000);

        try {
            account.withdraw(1500);
            throw new AssertionError("Expected IllegalArgumentException for insufficient funds.");
        } catch (IllegalArgumentException expected) {
            // test passed
        }
    }

    public void concurrentDeposits() throws Exception {
        Account account = new Account("AC1", "Riya", 0);
        Thread[] threads = new Thread[10];

        for (int i = 0; i < 10; i++) {
            threads[i] = new Thread(() -> {
                for (int j = 0; j < 1000; j++) {
                    account.deposit(1);
                }
            });
            threads[i].start();
        }

        for (Thread thread : threads) {
            thread.join();
        }

        if (account.getBalance() != 10000L) {
            throw new AssertionError("Expected total balance 10000 but got " + account.getBalance());
        }
    }
}
