package service;

import static util.Validator.isValidMobile;

import java.util.Scanner;

import model.Account;
import model.BankInfo;
import model.Command;
import model.CurrentAccount;
import model.FixedDepositAccount;
import model.SavingsAccount;
import model.WithdrawRule;
import util.Validator;

public class MiniBank {
    public static void main (String[] args) {

    BankInfo info = new BankInfo("Bank", "Surat");
    Account[] accounts = new Account[3];
    accounts[0] = new SavingsAccount("A", 1000, 500);
    accounts[1] = new CurrentAccount("B", 0, 1000);
    accounts[2] = new FixedDepositAccount("C", 500);

    WithdrawRule anonymousRule = new WithdrawRule() {
        @Override
        public boolean allow(Account account, long amount) {
            return account.canWithdraw(amount);
        }
    };
    WithdrawRule lambdaRule = (account, amount) -> account.canWithdraw(amount);
    System.out.println("Anonymous withdrawal rule allows 500: " + anonymousRule.allow(accounts[0], 500));
    System.out.println("Lambda withdrawal rule allows 500: " + lambdaRule.allow(accounts[0], 500));

    System.out.println("Account summaries:");
    for (Account account : accounts) {
        System.out.println(account);
        System.out.println("Interest rate: " + account.interestRate() + "%");
        System.out.println("Yearly interest: Rs. " + account.yearlyInterest());
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

    // --- Validator tests (one correct, one incorrect each) ---
    System.out.println("\nValidator tests:");
    System.out.println("Mobile valid (9876543210): " + isValidMobile("9876543210"));
    System.out.println("Mobile invalid (12345): " + Validator.isValidMobile("12345"));

    System.out.println("Email valid (alice@example.com): " + Validator.isValidEmail("alice@example.com"));
    System.out.println("Email invalid (alice@@example): " + Validator.isValidEmail("alice@@example"));

    System.out.println("PAN valid (ABCDE1234F): " + Validator.isValidPan("ABCDE1234F"));
    System.out.println("PAN invalid (abc123): " + Validator.isValidPan("abc123"));

    System.out.println("IFSC valid (SBIN0123456): " + Validator.isValidIfsc("SBIN0123456"));
    System.out.println("IFSC invalid (1234): " + Validator.isValidIfsc("1234"));

    // --- Command parse test ---
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