import java.time.LocalDate;
import java.util.*;

class ExpenseEntry {
    private String category;
    private double amount;
    private LocalDate date;

    public ExpenseEntry(String category, double amount, LocalDate date) {
        this.category = category;
        this.amount = amount;
        this.date = date;
    }

    public String getCategory() {
        return category;
    }

    public double getAmount() {
        return amount;
    }

    public LocalDate getDate() {
        return date;
    }

    @Override
    public String toString() {
        return date + " | " + category + " | ₹" + amount;
    }
}

class ExpenseTracker {
    private List<ExpenseEntry> expenses;

    public ExpenseTracker() {
        expenses = new ArrayList<>();
    }

    public void addExpense(String category, double amount, LocalDate date) {
        expenses.add(new ExpenseEntry(category, amount, date));
        System.out.println("✅ Expense added.");
    }

    public void showAllExpenses() {
        System.out.println("\n📋 All Expenses:");
        for (ExpenseEntry entry : expenses) {
            System.out.println(entry);
        }
    }

    public void showMonthlySummary(int month, int year) {
        double total = 0;
        Map<String, Double> categoryTotals = new HashMap<>();

        System.out.println("\n📊 Monthly Summary for " + year + "-" + String.format("%02d", month));
        for (ExpenseEntry entry : expenses) {
            if (entry.getDate().getMonthValue() == month && entry.getDate().getYear() == year) {
                total += entry.getAmount();
                categoryTotals.put(entry.getCategory(),
                        categoryTotals.getOrDefault(entry.getCategory(), 0.0) + entry.getAmount());
            }
        }

        for (String cat : categoryTotals.keySet()) {
            System.out.println(cat + ": ₹" + categoryTotals.get(cat));
        }
        System.out.println("Total: ₹" + total);
    }
}

public class ExpenseTrackerApp10 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ExpenseTracker tracker = new ExpenseTracker();

        // Sample data
        tracker.addExpense("Groceries", 1200.50, LocalDate.of(2025, 4, 5));
        tracker.addExpense("Transport", 300.00, LocalDate.of(2025, 4, 10));
        tracker.addExpense("Entertainment", 800.00, LocalDate.of(2025, 4, 12));
        tracker.addExpense("Groceries", 650.25, LocalDate.of(2025, 3, 20));

        tracker.showAllExpenses();

        System.out.print("\nEnter month (1-12) for summary: ");
        int month = sc.nextInt();
        System.out.print("Enter year: ");
        int year = sc.nextInt();

        tracker.showMonthlySummary(month, year);
    }
}

