package model;

import java.util.Objects;

import exception.AccountNotFoundException;
import exception.BankException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;
import model.annotation.Id;
import model.annotation.MaxLength;
import model.annotation.Positive;

public abstract class Account implements Transactable, InterestBearing {
    private static int accountCounter = 0;

    @Id
    private final String accountNumber;
    @MaxLength(50)
    private String ownerName;
    @Positive
    private long balance;
    private boolean active;

    public Account(String ownerName, long openingBalance) {
        this.accountNumber = generateAccountNumber();
        this.ownerName = ownerName;
        this.balance = openingBalance;
        this.active = true;
    }

    public Account(String ownerName) {
        this(ownerName, 0);
    }

    private static String generateAccountNumber() {
        accountCounter++;
        return "AC" + String.format("%04d", accountCounter);
    }

    @Override
    public void deposit(long amount) throws InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Deposit amount must be greater than zero.");
        }
        balance += amount;
    }

    @Override
    public void withdraw(long amount) throws InsufficientFundsException, InvalidAmountException {
        if (amount <= 0) {
            throw new InvalidAmountException("Withdrawal amount must be greater than zero.");
        }
        if (!canWithdraw(amount)) {
            long availableFunds = availableFunds();
            long shortfall = amount > availableFunds ? amount - availableFunds : 0;
            throw new InsufficientFundsException(shortfall);
        }
        balance -= amount;
    }

    public void transfer(Account to, long amount) throws BankException {
        boolean withdrawn = false;
        boolean completed = false;
        try {
            if (to == null) {
                throw new AccountNotFoundException("Destination account was not found.");
            }
            withdraw(amount);
            withdrawn = true;
            to.deposit(amount);
            completed = true;
        } catch (BankException exception) {
            throw exception;
        } finally {
            if (withdrawn && !completed) {
                balance += amount;
            }
        }
    }

    @Override
    public abstract double interestRate();

    public abstract boolean canWithdraw(long amount);

    protected long availableFunds() {
        return Math.max(0, balance);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getOwnerName() {
        return ownerName;
    }

    @Override
    public long getBalance() {
        return balance;
    }

    public boolean isActive() {
        return active;
    }

    @Override
    public String toString() {
        return accountNumber + " - " + ownerName + " - Rs. " + balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Account)) {
            return false;
        }
        Account other = (Account) o;
        return Objects.equals(accountNumber, other.accountNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountNumber);
    }
}
