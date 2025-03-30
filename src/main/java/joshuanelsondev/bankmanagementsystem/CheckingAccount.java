package joshuanelsondev.bankmanagementsystem;

public class CheckingAccount extends BankAccount {
    private double overdraftLimit;

    public CheckingAccount(String accountNumber, double balance, double overdraftLimit) {
        super(accountNumber, balance);
        this.overdraftLimit = overdraftLimit;
    }

    @Override
    public void withdraw(double amount) {
       double currentBalance = getBalance();

       if (currentBalance + overdraftLimit >= amount) {
           System.out.println("Withdrawing: " + amount);
           deposit(-amount);
       } else {
           System.out.println("Exceeds overdraft limit.");
       }
    }
}
