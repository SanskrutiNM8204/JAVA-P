package model;
import model.annotation.Id;
public class Account {
    @Id
    private final String accountNumber;
    private final String ownerName;
    private long balance;
    public Account(String accountNumber, String ownerName, long balance) {
        this.accountNumber = accountNumber;
        this.ownerName = ownerName;
        this.balance = balance;
    }
    public long getBalance() { return balance; }
    public String toString() {
        return accountNumber + " - " + ownerName + " - Rs. " + balance;
    }
}
