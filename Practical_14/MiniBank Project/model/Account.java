package model;
public class Account {
    private final String accountNumber;
    private final String ownerName;
    private long balance;
    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public synchronized void deposit(long amount) { balance += amount; }
    public synchronized void withdraw(long amount) {
        if (amount > balance) throw new IllegalArgumentException("Insufficient funds");
        balance -= amount;
    }
    public synchronized long getBalance() { return balance; }
    public String getAccountNumber() { return accountNumber; }
    public String toString() { return accountNumber + " - " + ownerName + " - Rs. " + balance; }
}
