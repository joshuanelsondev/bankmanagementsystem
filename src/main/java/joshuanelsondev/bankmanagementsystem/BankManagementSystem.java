package joshuanelsondev.bankmanagementsystem;

public class BankManagementSystem {
    public static void main(String[] args) {
        System.out.println("Bank Management System");
        BankService bankService = new BankService();
        InputUtils inputUtils = new InputUtils();

        boolean exit = false;

        while(!exit) {
            System.out.println("\nSimple Bank Account Management System");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Check Balance");
            System.out.println("5. Exit");
            System.out.println();
            System.out.print("Enter your choice: ");

            int choice = inputUtils.readInt();
            System.out.println();

            switch(choice) {
                case 1:
                    bankService.createAccount();
                    break;
                case 2:
                    bankService.depositMoney();
                    break;
                case 3:
                    bankService.withdrawMoney();
                    break;
                case 4:
                    bankService.checkBalance();
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
