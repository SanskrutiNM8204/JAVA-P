package service;
import java.util.*;
import model.Account;
public class BankService {
    private final Map<String, Account> accounts = new HashMap<>();
    public void openAccount(Account account) { accounts.put(account.getAccountNumber(), account); }
    public void deposit(String id, long amount) { accounts.get(id).deposit(amount); }
    public void withdraw(String id, long amount) { accounts.get(id).withdraw(amount); }
    public void transfer(String from, String to, long amount) {
        Account source = accounts.get(from);
        Account destination = accounts.get(to);
        synchronized (source) {
            synchronized (destination) {
                source.withdraw(amount);
                destination.deposit(amount);
            }
        }
    }
    public Account getAccount(String id) { return accounts.get(id); }
    public void listAccounts() {
        for (Account account : accounts.values()) System.out.println(account);
    }
}
