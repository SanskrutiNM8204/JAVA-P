package model;

public class Account implements Comparable<Account> {

    private String accountNumber;
    private String holderName;
    private double balance;

    public Account(String accountNumber, String holderName, double balance) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = balance;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    @Override
    public int compareTo(Account other) {
        return Double.compare(this.balance, other.balance);
    }

    @Override
    public String toString() {
        return accountNumber + " - " + holderName + " - Rs. " + balance;
    }
}