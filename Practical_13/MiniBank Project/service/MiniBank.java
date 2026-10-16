package service;
import model.Account;
public class MiniBank {
    public static void main(String[] args) {
        Repository<Account> repository = new Repository<>();
        Account a1 = new Account("AC1001", "Riya", 1000);
        Account a2 = new Account("AC1002", "Aman", 2000);
        Account a3 = new Account("AC1003", "Karan", 3000);
        repository.saveAll(a1, a2, a3);
        System.out.println("Found: " + repository.findById("AC1002"));
        System.out.println("All accounts:");
        for (Account account : repository.findAll()) System.out.println(account);
        Integer[] values = {1000, 2000, 3000};
        System.out.println("Average balance: " + Repository.average(values));
    }
}
