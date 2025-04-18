import java.util.*;

class UserAccount {
    private String accountNumber;
    private int pin;
    private double balance;

    UserAccount(String accountNumber, int pin, double balance) {
        this.accountNumber = accountNumber;
        this.pin = pin;
        this.balance = balance;
    }

    public boolean validatePin(int inputPin) {
        return this.pin == inputPin;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        balance += amount;
        System.out.println("₹" + amount + " deposited successfully.");
    }

    public void withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid amount!");
            return;
        }
        if (amount > balance) {
            System.out.println("Insufficient balance!");
            return;
        }
        balance -= amount;
        System.out.println("₹" + amount + " withdrawn successfully.");
    }

    public String getAccountNumber() {
        return accountNumber;
    }
}

class ATM {
    private Map<String, UserAccount> users;

    ATM() {
        users = new HashMap<>();
        // Adding dummy users
        users.put("123456", new UserAccount("123456", 1111, 5000.00));
        users.put("987654", new UserAccount("987654", 2222, 10000.00));
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Account Number: ");
        String accNum = sc.nextLine();

        if (!users.containsKey(accNum)) {
            System.out.println("Account not found.");
            return;
        }

        System.out.print("Enter PIN: ");
        int pin = sc.nextInt();
        UserAccount user = users.get(accNum);

        if (!user.validatePin(pin)) {
            System.out.println("Invalid PIN.");
            return;
        }

        System.out.println("Login successful!");

        while (true) {
            System.out.println("\n1. Check Balance\n2. Deposit\n3. Withdraw\n4. Exit");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your balance: ₹" + user.getBalance());
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double dep = sc.nextDouble();
                    user.deposit(dep);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = sc.nextDouble();
                    user.withdraw(wd);
                    break;
                case 4:
                    System.out.println("Thank you for using the ATM.");
                    return;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }
}

public class ATMMachineSimulator03 {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.start();
    }
}

