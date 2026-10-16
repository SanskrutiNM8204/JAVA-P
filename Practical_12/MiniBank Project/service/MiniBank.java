package service;

import model.Account;

public class MiniBank {

    public static void main(String[] args) {

        AccountStore store = new AccountStore();

        store.add(new Account("AC1001", "Riya", 3000));
        store.add(new Account("AC1002", "Aman", 1000));
        store.add(new Account("AC1003", "Karan", 5000));

        System.out.println("Found: " + store.get("AC1001"));

        System.out.println("Sorted accounts:");

        for (Account account : store.sortedByBalance()) {
            System.out.println(account);
        }

        System.out.println("History: " + store.getHistory());
    }
}