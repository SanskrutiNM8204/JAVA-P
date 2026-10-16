package service;

import model.Account;
import java.util.*;

public class AccountStore {

    private HashMap<String, Account> accounts = new HashMap<>();
    private ArrayList<String> history = new ArrayList<>();

    public void add(Account account) {
        accounts.put(account.getAccountNumber(), account);
        history.add("Added " + account.getAccountNumber());
    }

    public Account get(String accountId) {
        return accounts.get(accountId);
    }

    public List<Account> sortedByBalance() {
        List<Account> sorted = new ArrayList<>(accounts.values());

        sorted.sort(
            Comparator.comparingDouble(Account::getBalance).reversed()
        );

        return sorted;
    }

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}