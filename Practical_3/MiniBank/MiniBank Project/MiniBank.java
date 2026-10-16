import java.util.Scanner;

public class MiniBank {
    public static void main (String[] args) {

    BankInfo info = new BankInfo("Bank", "Surat");

    Account[] accounts = new Account[3];
    accounts[0] = new Account("A", 1000);
    accounts[1] = new Account("B");
    accounts[2] = new Account("C", 500);

    System.out.println("Account summaries:");
    for (Account account : accounts) {
        System.out.println(account);
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
            case 1 -> {
                System.out.println("Open Account - feature coming in a later lab.");
            }
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
            case 4 -> {
                System.out.println("Transfer - feature coming in a later lab.");
            }
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


