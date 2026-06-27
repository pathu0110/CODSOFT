import java.util.Scanner;

class Account {

    private double balance;

    public Account(double balance) {
        this.balance = balance;
    }

    public boolean withdrawMoney(double amount) {
        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return false;
        }

        if (amount > balance) {
            System.out.println("Transaction Failed! Insufficient funds.");
            return false;
        }

        balance -= amount;
        return true;
    }

    public boolean depositMoney(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid deposit amount.");
            return false;
        }

        balance += amount;
        return true;
    }

    public double balanceInquiry() {
        return balance;
    }
}

class ATMMachine {

    private Account userAccount;
    private Scanner input = new Scanner(System.in);

    public ATMMachine(Account userAccount) {
        this.userAccount = userAccount;
    }

    public void displayMenu() {

        int option;

        do {
            System.out.println("\n============================");
            System.out.println("        SIMPLE ATM");
            System.out.println("============================");
            System.out.println("1. Balance Inquiry");
            System.out.println("2. Cash Deposit");
            System.out.println("3. Cash Withdrawal");
            System.out.println("4. Exit");
            System.out.print("Select Option: ");

            option = input.nextInt();

            switch (option) {

                case 1:
                    System.out.printf("Current Balance: $%.2f%n",
                            userAccount.balanceInquiry());
                    break;

                case 2:
                    depositProcess();
                    break;

                case 3:
                    withdrawProcess();
                    break;

                case 4:
                    System.out.println("Thank you for using our ATM.");
                    break;

                default:
                    System.out.println("Invalid option! Try again.");
            }

        } while (option != 4);
    }

    private void depositProcess() {
        System.out.print("Enter amount to deposit: ");
        double amount = input.nextDouble();

        if (userAccount.depositMoney(amount)) {
            System.out.println("Deposit Completed Successfully.");
        }
    }

    private void withdrawProcess() {
        System.out.print("Enter amount to withdraw: ");
        double amount = input.nextDouble();

        if (userAccount.withdrawMoney(amount)) {
            System.out.println("Please collect your cash.");
        }
    }
}

public class ATMApplication {

    public static void main(String[] args) {

        Account myAccount = new Account(2500.00);

        ATMMachine atm = new ATMMachine(myAccount);

        atm.displayMenu();
    }
}