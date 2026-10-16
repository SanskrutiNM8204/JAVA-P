package service;
import java.util.Scanner;
import model.Account;
public class MiniBank {
    public static void main(String[] args) {
        BankService bank = new BankService();
        bank.openAccount(new Account("AC1001", "Riya", 1000));
        bank.openAccount(new Account("AC1002", "Aman", 2000));
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Show Accounts");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            int choice = Integer.parseInt(scanner.nextLine());
            try {
                if (choice == 1) {
                    System.out.print("Account: ");
                    String id = scanner.nextLine();
                    System.out.print("Amount: ");
                    bank.deposit(id, Long.parseLong(scanner.nextLine()));
                    System.out.println("Deposit successful");
                } else if (choice == 2) {
                    System.out.print("Account: ");
                    String id = scanner.nextLine();
                    System.out.print("Amount: ");
                    bank.withdraw(id, Long.parseLong(scanner.nextLine()));
                    System.out.println("Withdrawal successful");
                } else if (choice == 3) {
                    System.out.print("From: ");
                    String from = scanner.nextLine();
                    System.out.print("To: ");
                    String to = scanner.nextLine();
                    System.out.print("Amount: ");
                    bank.transfer(from, to, Long.parseLong(scanner.nextLine()));
                    System.out.println("Transfer successful");
                } else if (choice == 4) {
                    bank.listAccounts();
                } else if (choice == 5) {
                    break;
                } else {
                    System.out.println("Invalid choice");
                }
            } catch (Exception e) {
                System.out.println("Operation failed: " + e.getMessage());
            }
        }
        scanner.close();
    }
}
