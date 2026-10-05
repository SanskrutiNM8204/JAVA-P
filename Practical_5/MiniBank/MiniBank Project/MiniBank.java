import java.util.Locale;
import java.util.Objects;
import java.util.Scanner;

public class MiniBank {
    private record BankInfo(String name, String address) {}

    private abstract static class Account {
        private static int nextSequence = 1;
        private final String accountNumber;
            private final String holderName;
            protected long balance;

        protected Account(String holderName, long balance) {
            this.accountNumber = "AC" + String.format("%04d", nextSequence++);
            this.holderName = holderName;
            this.balance = balance;
        }

        public String getAccountNumber() {
            return accountNumber;
        }

        public String getHolderName() {
            return holderName;
        }

        public long getBalance() {
            return balance;
        }

        public void deposit(long amount) {
            if (amount > 0) {
                balance += amount;
            }
        }

        public boolean withdraw(long amount) {
            if (amount <= 0 || amount > balance) {
                return false;
            }
            balance -= amount;
            return true;
        }

        public abstract double interestRate();

        public abstract String accountType();

        @Override
        public String toString() {
            return accountType() + " [" + accountNumber + "] - " + holderName + ", Balance: Rs. " + balance;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Account other)) {
                return false;
            }
            return accountNumber.equals(other.accountNumber) && holderName.equals(other.holderName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(accountNumber, holderName);
        }
    }

    private static class SavingsAccount extends Account {
        private final long minimumBalance;

        public SavingsAccount(String holderName, long balance, long minimumBalance) {
            super(holderName, balance);
            this.minimumBalance = minimumBalance;
        }

        public long getMinimumBalance() {
            return minimumBalance;
        }

        @Override
        public double interestRate() {
            return 4.5;
        }

        @Override
        public String accountType() {
            return "SavingsAccount";
        }

        @Override
        public boolean withdraw(long amount) {
            if (amount <= 0 || amount > balance || (balance - amount) < minimumBalance) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    private static class CurrentAccount extends Account {
        private final long overdraftLimit;

        public CurrentAccount(String holderName, long balance, long overdraftLimit) {
            super(holderName, balance);
            this.overdraftLimit = overdraftLimit;
        }

        public long getOverdraftLimit() {
            return overdraftLimit;
        }

        @Override
        public double interestRate() {
            return 3.0;
        }

        @Override
        public String accountType() {
            return "CurrentAccount";
        }

        @Override
        public boolean withdraw(long amount) {
            if (amount <= 0 || amount > (balance + overdraftLimit)) {
                return false;
            }
            balance -= amount;
            return true;
        }
    }

    private static class FixedDepositAccount extends Account {
        public FixedDepositAccount(String holderName, long balance) {
            super(holderName, balance);
        }

        @Override
        public double interestRate() {
            return 7.5;
        }

        @Override
        public String accountType() {
            return "FixedDepositAccount";
        }

        @Override
        public boolean withdraw(long amount) {
            return false;
        }
    }

    private static final class Validator {
        private Validator() {}

        public static boolean isValidMobile(String mobile) {
            return mobile != null && mobile.matches("\\d{10}");
        }

        public static boolean isValidEmail(String email) {
            return email != null && email.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$");
        }

        public static boolean isValidPan(String pan) {
            return pan != null && pan.matches("^[A-Z]{5}[0-9]{4}[A-Z]$");
        }

        public static boolean isValidIfsc(String ifsc) {
            return ifsc != null && ifsc.matches("^[A-Z]{4}0[A-Z0-9]{6}$");
        }
    }

    public record Command(String type, String accountNumber, long amount) {}

    public static final class CommandParser {
        private CommandParser() {}

        public static Command parse(String commandText) {
            if (commandText == null || commandText.isBlank()) {
                throw new IllegalArgumentException("Command cannot be empty.");
            }

            String[] parts = commandText.trim().split("\\s+");
            if (parts.length != 3) {
                throw new IllegalArgumentException("Command format is invalid. Expected: TYPE ACCOUNT_NUMBER AMOUNT");
            }

            String type = parts[0].toUpperCase(Locale.ROOT);
            if (!type.equals("DEPOSIT") && !type.equals("WITHDRAW") && !type.equals("TRANSFER")) {
                throw new IllegalArgumentException("Unsupported command type: " + type);
            }

            String accountNumber = parts[1];
            long amount;
            try {
                amount = Long.parseLong(parts[2]);
            } catch (NumberFormatException e) {
                throw new IllegalArgumentException("Amount must be numeric.");
            }

            return new Command(type, accountNumber, amount);
        }
    }

    public static void main(String[] args) {
        BankInfo info = new BankInfo("Bank", "Surat");
        Account[] accounts = new Account[3];
        accounts[0] = new SavingsAccount("A", 1000, 500);
        accounts[1] = new CurrentAccount("B", 0, 1000);
        accounts[2] = new FixedDepositAccount("C", 500);

        System.out.println("Account summaries:");
        for (Account account : accounts) {
            System.out.println(account);
            System.out.println("Interest rate: " + account.interestRate() + "%");
            if (account instanceof SavingsAccount savingsAccount) {
                System.out.println("Savings account balance: Rs. " + savingsAccount.getBalance());
            }
        }

        System.out.println("\nAccount comparison tests:");
        System.out.println("accounts[0].equals(accounts[1]) = " + accounts[0].equals(accounts[1]));
        System.out.println("accounts[0].equals(accounts[0]) = " + accounts[0].equals(accounts[0]));
        Object someObject = accounts[0];
        System.out.println("someObject instanceof Account = " + (someObject instanceof Account));

        System.out.println("======================");
        System.out.println("Welcome to MiniBank!");
        System.out.println("======================");
        System.out.println("  " + info.name() + " - " + info.address());

        System.out.println("\nValidator tests:");
        System.out.println("Mobile valid (9876543210): " + Validator.isValidMobile("9876543210"));
        System.out.println("Mobile invalid (12345): " + Validator.isValidMobile("12345"));

        System.out.println("Email valid (alice@example.com): " + Validator.isValidEmail("alice@example.com"));
        System.out.println("Email invalid (alice@@example): " + Validator.isValidEmail("alice@@example"));

        System.out.println("PAN valid (ABCDE1234F): " + Validator.isValidPan("ABCDE1234F"));
        System.out.println("PAN invalid (abc123): " + Validator.isValidPan("abc123"));

        System.out.println("IFSC valid (SBIN0123456): " + Validator.isValidIfsc("SBIN0123456"));
        System.out.println("IFSC invalid (1234): " + Validator.isValidIfsc("1234"));

        System.out.println("\nCommand parse test:");
        try {
            Command cmd = CommandParser.parse("DEPOSIT AC0001 500");
            System.out.println("Type: " + cmd.type() + ", Account: " + cmd.accountNumber() + ", Amount: " + cmd.amount());
        } catch (IllegalArgumentException e) {
            System.out.println("Failed to parse command: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        while (running) {
            printMenu();
            System.out.print("Enter your choice: ");
            String choiceInput = scanner.nextLine().trim();

            if (choiceInput.isEmpty()) {
                continue;
            }

            int choice;
            try {
                choice = Integer.parseInt(choiceInput);
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again.");
                continue;
            }

            switch (choice) {
                case 1 -> System.out.println("Open Account - feature coming in a later lab.");
                case 2 -> {
                    System.out.print("Enter account number (1-3): ");
                    int depositAccountIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    System.out.print("Enter amount to deposit: ");
                    long depositAmount = Long.parseLong(scanner.nextLine().trim());

                    if (depositAccountIndex >= 0 && depositAccountIndex < accounts.length) {
                        accounts[depositAccountIndex].deposit(depositAmount);
                        System.out.println("Deposit successful.");
                        System.out.println("New balance: Rs. " + accounts[depositAccountIndex].getBalance());
                    } else {
                        System.out.println("Invalid account number.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter account number (1-3): ");
                    int withdrawAccountIndex = Integer.parseInt(scanner.nextLine().trim()) - 1;
                    System.out.print("Enter amount to withdraw: ");
                    long withdrawAmount = Long.parseLong(scanner.nextLine().trim());

                    if (withdrawAccountIndex >= 0 && withdrawAccountIndex < accounts.length) {
                        boolean success = accounts[withdrawAccountIndex].withdraw(withdrawAmount);
                        if (success) {
                            System.out.println("Withdrawal successful.");
                            System.out.println("New balance: Rs. " + accounts[withdrawAccountIndex].getBalance());
                        } else {
                            System.out.println("Insufficient balance. Withdrawal failed.");
                        }
                    } else {
                        System.out.println("Invalid account number.");
                    }
                }
                case 4 -> System.out.println("Transfer - feature coming in a later lab.");
                case 5 -> {
                    System.out.println("Exiting the program. Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
        scanner.close();
    }

    private static void printMenu() {
        System.out.println("\nPlease select an option:");
        System.out.println("1. Open Account");
        System.out.println("2. Deposit");
        System.out.println("3. Withdraw");
        System.out.println("4. Transfer");
        System.out.println("5. Exit");
    }
}