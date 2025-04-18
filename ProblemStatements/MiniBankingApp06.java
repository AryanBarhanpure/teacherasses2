import java.util.*;

class BankAccount {
    private String accountNumber;
    private String holderName;
    private double balance;

    public BankAccount(String accountNumber, String holderName, double initialDeposit) {
        this.accountNumber = accountNumber;
        this.holderName = holderName;
        this.balance = initialDeposit;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getHolderName() {
        return holderName;
    }

    public double getBalance() {
        return balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("✅ ₹" + amount + " deposited.");
        } else {
            System.out.println("❌ Invalid deposit amount.");
        }
    }

    public void withdraw(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            System.out.println("✅ ₹" + amount + " withdrawn.");
        } else {
            System.out.println("❌ Insufficient balance or invalid amount.");
        }
    }

    public void transferTo(BankAccount receiver, double amount) {
        if (amount > 0 && amount <= balance) {
            this.withdraw(amount);
            receiver.deposit(amount);
            System.out.println("✅ ₹" + amount + " transferred to " + receiver.getHolderName());
        } else {
            System.out.println("❌ Transfer failed.");
        }
    }

    public void printStatement() {
        System.out.println("\n📄 Account Statement");
        System.out.println("Account No.: " + accountNumber);
        System.out.println("Name       : " + holderName);
        System.out.println("Balance    : ₹" + balance);
        System.out.println("---------------------------");
    }
}

class BankSystem {
    private Map<String, BankAccount> accounts = new HashMap<>();

    public void createAccount(String accNo, String name, double deposit) {
        if (accounts.containsKey(accNo)) {
            System.out.println("❌ Account number already exists.");
        } else {
            accounts.put(accNo, new BankAccount(accNo, name, deposit));
            System.out.println("✅ Account created for " + name);
        }
    }

    public BankAccount getAccount(String accNo) {
        return accounts.get(accNo);
    }

    public void listAllAccounts() {
        System.out.println("\n🏦 All Bank Accounts:");
        for (BankAccount acc : accounts.values()) {
            acc.printStatement();
        }
    }
}

public class MiniBankingApp06 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankSystem bank = new BankSystem();

        while (true) {
            System.out.println("\n==== Mini Banking System ====");
            System.out.println("1. Create Account");
            System.out.println("2. Deposit");
            System.out.println("3. Withdraw");
            System.out.println("4. Transfer");
            System.out.println("5. View Statement");
            System.out.println("6. List All Accounts");
            System.out.println("7. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter Account No.: ");
                    String acc = sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Initial Deposit: ");
                    double dep = sc.nextDouble();
                    bank.createAccount(acc, name, dep);
                    break;
                case 2:
                    System.out.print("Enter Account No.: ");
                    acc = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    double amount = sc.nextDouble();
                    BankAccount accDep = bank.getAccount(acc);
                    if (accDep != null) accDep.deposit(amount);
                    else System.out.println("❌ Account not found.");
                    break;
                case 3:
                    System.out.print("Enter Account No.: ");
                    acc = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    amount = sc.nextDouble();
                    BankAccount accWith = bank.getAccount(acc);
                    if (accWith != null) accWith.withdraw(amount);
                    else System.out.println("❌ Account not found.");
                    break;
                case 4:
                    System.out.print("Enter Sender Account No.: ");
                    String senderAcc = sc.nextLine();
                    System.out.print("Enter Receiver Account No.: ");
                    String receiverAcc = sc.nextLine();
                    System.out.print("Enter Amount: ");
                    amount = sc.nextDouble();
                    BankAccount sender = bank.getAccount(senderAcc);
                    BankAccount receiver = bank.getAccount(receiverAcc);
                    if (sender != null && receiver != null) {
                        sender.transferTo(receiver, amount);
                    } else {
                        System.out.println("❌ One or both accounts not found.");
                    }
                    break;
                case 5:
                    System.out.print("Enter Account No.: ");
                    acc = sc.nextLine();
                    BankAccount accStat = bank.getAccount(acc);
                    if (accStat != null) accStat.printStatement();
                    else System.out.println("❌ Account not found.");
                    break;
                case 6:
                    bank.listAllAccounts();
                    break;
                case 7:
                    System.out.println("👋 Thank you for banking with us!");
                    return;
                default:
                    System.out.println("❌ Invalid option.");
            }
        }
    }
}

