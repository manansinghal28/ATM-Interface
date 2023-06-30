import java.util.Scanner;

public class BankAccount {
    private int accountNumber;
    private String customerName;
    private String email;
    private String phoneNumber;
    private double balance;
    private int transactionsCount;
    private String[] transactions;

    public BankAccount(int accountNumber, String customerName, String email, String phoneNumber, double balance) {
        this.accountNumber = accountNumber;
        this.customerName = customerName;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.transactionsCount = 0;
        this.transactions = new String[10];
    }

    public void deposit(double amount) {
        balance += amount;
        transactions[transactionsCount++] = "Deposit: " + amount;
        System.out.println("Deposit of " + amount + " made. New balance is " + balance);
    }

    public void withdraw(double amount) {
        if (balance - amount < 0) {
            System.out.println("Insufficient balance. Cannot withdraw.");
            return;
        }
        balance -= amount;
        transactions[transactionsCount++] = "Withdraw: " + amount;
        System.out.println("Withdrawal of " + amount + " made. New balance is " + balance);
    }

    public void transfer(double amount, BankAccount destinationAccount) {
        if (balance - amount < 0) {
            System.out.println("Insufficient balance. Cannot transfer.");
            return;
        }
        balance -= amount;
        destinationAccount.deposit(amount);
        transactions[transactionsCount++] = "Transfer: " + amount + " to " + destinationAccount.customerName;
        System.out.println("Transfer of " + amount + " made to " + destinationAccount.customerName + ". New balance is " + balance);
    }

    public void printTransactions() {
        System.out.println("\nTransaction history for account #" + accountNumber + ":");
        for (int i = 0; i < transactionsCount; i++) {
            System.out.println(transactions[i]);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter account details:");
        System.out.print("Account number: ");
        int accountNumber = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Customer name: ");
        String customerName = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone number: ");
        String phoneNumber = scanner.nextLine();
        System.out.print("Starting balance: ");
        double balance = scanner.nextDouble();
        BankAccount account = new BankAccount(accountNumber, customerName, email, phoneNumber, balance);

        int choice = 0;
        while (choice != 5) {
            System.out.println("\nEnter choice:");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Transfer");
            System.out.println("4. Transaction history");
            System.out.println("5. Quit");

            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.print("\nEnter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    account.deposit(depositAmount);
                    break;
                case 2:
                    System.out.print("\nEnter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    account.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("\nEnter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    System.out.print("\nEnter destination account number: ");
                    int destinationAccountNumber = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("\nEnter destination account name: ");
                    String destinationAccountName = scanner.nextLine();
                    System.out.print("\nEnter destination account email: ");
                    String destinationAccountEmail = scanner.nextLine();
                    System.out.print("\nEnter destination account phone number: ");
                    String destinationAccountPhoneNumber = scanner.nextLine();
                    BankAccount destinationAccount = new BankAccount(destinationAccountNumber, destinationAccountName, destinationAccountEmail, destinationAccountPhoneNumber, 0);
                    account.transfer(transferAmount, destinationAccount);
                    break;
                case 4:
                    account.printTransactions();
                    break;
                case 5:
                    System.out.println("\nGoodbye!");
                    break;
                default:
                    System.out.println("\nInvalid choice. Please enter a number between 1 and 5.");
                    break;
            }
        }
        scanner.close();
    }
}
