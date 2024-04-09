package examples;
public class BankAccount {
    // Non-static variable representing the account balance
    double balance;

    // Constructor to initialize the balance when an account is created
    public BankAccount(double initialBalance) {
        balance = initialBalance;
    }

    // Non-static method to deposit money into the account
    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit: " + amount);
    }

    // Non-static method to withdraw money from the account
    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal: " + amount);
        } else {
            System.out.println("Insufficient funds for withdrawal.");
        }
    }

    // Non-static method to check the balance of the account
    void checkBalance() {
        System.out.println("Current Balance: " + balance);
    }

    public static void main(String[] args) {
        // Creating instances of the class to represent bank accounts
        BankAccount account1 = new BankAccount(1000.0);
        account1.checkBalance();


        account1.deposit(500.0);
        account1.checkBalance();

        account1.withdraw(200.0);
        account1.checkBalance();
    }
}