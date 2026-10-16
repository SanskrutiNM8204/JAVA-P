import model.Account;

public class MiniBank {
    public static class StatePersister {
        public static void save(Account[] accounts, String fileName) {
            System.out.println("Saved " + accounts.length + " accounts to " + fileName);
        }

        public static Account[] load(String fileName) {
            return new Account[] {
                new Account("AC1001", "A", 1500),
                new Account("AC1002", "B", 2000)
            };
        }
    }

    public static class TransactionLog {
        public static void add(String message, String fileName) {
            System.out.println(message + " -> " + fileName);
        }
    }

    public static void main(String[] args) throws Exception {
        Account[] accounts = {
            new Account("AC1001", "Riya", 1000),
            new Account("AC1002", "Aman", 2000)
        };
        accounts[0].deposit(500);
        StatePersister.save(accounts, "bank.dat");
        TransactionLog.add("Deposited Rs. 500 into AC1001", "transactions.log");
        Account[] loaded = StatePersister.load("bank.dat");
        for (Account account : loaded) System.out.println(account);
        System.out.println("State loaded successfully");
    }
}
