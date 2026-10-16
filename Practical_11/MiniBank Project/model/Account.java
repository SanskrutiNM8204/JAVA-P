package model;
import java.io.Serializable;
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String accountNumber;
    private final String ownerName;
    private long balance;
    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public String getAccountNumber() { return accountNumber; }
    public long getBalance() { return balance; }
    public void deposit(long amount) { balance += amount; }
    public String toString() {
        return accountNumber + " - " + ownerName + " - Rs. " + balance;
    }
}
