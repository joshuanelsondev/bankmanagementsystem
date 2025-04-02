package joshuanelsondev.bankmanagementsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class BankService {
    private final List<BankAccount> accounts;
    private final InputUtils inputUtils;

    public BankService(InputUtils inputUtils) {
        this.accounts = new ArrayList<>();
        this.inputUtils = inputUtils;
    }

    public void createAccount() {
        String accountNumber;
        boolean accountNumberExists;

        do {
            System.out.print("Enter account number: ");
            System.out.println(inputUtils);
            try {
                accountNumber = inputUtils.readString();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please try again.");
                return;
            }
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
        double balance;
        try {
            balance = inputUtils.readDouble();
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please try again.");
            return;
        }
        System.out.println();

        System.out.println("Select account type: ");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.println();
        System.out.print("Enter your choice: ");
        int accountType;
        try {
            accountType = inputUtils.readInt();
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please try again.");
            return;
        }


        switch (accountType) {
            case 1:
                accounts.add(new SavingsAccount(accountNumber, balance));
                break;
            case 2:
                System.out.print("Enter overdraft limit: ");
                double overdraftLimit;
                try {
                    overdraftLimit = inputUtils.readDouble();
                } catch (NoSuchElementException e) {
                    System.out.println("Invalid input. Please try again.");
                    return;
                }
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
            double amount;
            try {
                amount = inputUtils.readDouble();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please try again.");
                return;
            }
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
            double amount;
            try {
                amount = inputUtils.readDouble();
            } catch (NoSuchElementException e) {
                System.out.println("Invalid input. Please try again.");
                return;
            }
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
        String accountNumber;
        try {
            accountNumber = inputUtils.readString();
        } catch (NoSuchElementException e) {
            System.out.println("Invalid input. Please try again.");
            return null;
        }
        System.out.println();

        for (BankAccount account: accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }

        return null;
    }

}
