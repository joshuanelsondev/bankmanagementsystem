package joshuanelsondev.bankmanagementsystem;

import java.util.ArrayList;
import java.util.List;

public class BankService {
    private final List<BankAccount> accounts;
    private final InputUtils inputUtils;

    public BankService() {
        this.accounts = new ArrayList<>();
        this.inputUtils = new InputUtils();
    }

    public void createAccount() {
        String accountNumber;
        boolean accountNumberExists;

        do {
            System.out.print("Enter account number: ");
            accountNumber = inputUtils.readString();
            accountNumberExists = false;

            for (BankAccount account : accounts) {
                if (account.getAccountNumber().equals(accountNumber)) {
                    System.out.println("Account number already exists. Please enter a different number.");
                    accountNumberExists = true;
                    break;
                }
            }
        } while (accountNumberExists);

        System.out.println();


        System.out.print("Enter initial balance: ");
        double balance = inputUtils.readDouble();
        System.out.println();

        System.out.println("Select account type: ");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.println();
        System.out.print("Enter your choice: ");
        int accountType = inputUtils.readInt();

        switch (accountType) {
            case 1:
                accounts.add(new SavingsAccount(accountNumber, balance));
                break;
            case 2:
                System.out.print("Enter overdraft limit: ");
                double overdraftLimit = inputUtils.readDouble();
                accounts.add(new CheckingAccount(accountNumber, balance, overdraftLimit));
                break;
            default:
                System.out.println("Invalid account type");
                break;
        }

        System.out.println("Account created successfully.");
    }

    public void depositMoney() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Enter amount to deposit: ");
            double amount = inputUtils.readDouble();
            account.deposit(amount);
            System.out.println();
        } else {
            System.out.println("Account not found.");
        }
    }

    public void withdrawMoney() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Enter amount to withdraw: ");
            double amount = inputUtils.readDouble();
            account.withdraw(amount);
        } else {
            System.out.println("Account not found.");
        }
    }

    public void checkBalance() {
        BankAccount account = findAccount();
        if (account != null) {
            System.out.print("Current balance: " + account.getBalance());
            System.out.println();
        } else {
            System.out.println("Account not found.");
        }
    }

    private BankAccount findAccount() {
        System.out.print("Enter account number: ");
        String accountNumber = inputUtils.readString();
        System.out.println();

        for (BankAccount account: accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

}
