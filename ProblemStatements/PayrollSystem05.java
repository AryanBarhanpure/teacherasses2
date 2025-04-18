import java.util.*;

class Employee {
    private int id;
    private String name;
    private double hourlyRate;
    private int hoursWorked;
    private double bonus;

    public Employee(int id, String name, double hourlyRate, int hoursWorked, double bonus) {
        this.id = id;
        this.name = name;
        this.hourlyRate = hourlyRate;
        this.hoursWorked = hoursWorked;
        this.bonus = bonus;
    }

    public double calculateGrossSalary() {
        return hourlyRate * hoursWorked + bonus;
    }

    public double calculateTax() {
        double gross = calculateGrossSalary();
        if (gross <= 10000) return gross * 0.1;
        else if (gross <= 20000) return gross * 0.15;
        else return gross * 0.2;
    }

    public double calculateNetSalary() {
        return calculateGrossSalary() - calculateTax();
    }

    public void displayPaySlip() {
        System.out.println("------------------------------------------------");
        System.out.println("Employee ID     : " + id);
        System.out.println("Name            : " + name);
        System.out.println("Hourly Rate     : ₹" + hourlyRate);
        System.out.println("Hours Worked    : " + hoursWorked);
        System.out.println("Bonus           : ₹" + bonus);
        System.out.println("Gross Salary    : ₹" + calculateGrossSalary());
        System.out.println("Tax Deducted    : ₹" + calculateTax());
        System.out.println("Net Salary      : ₹" + calculateNetSalary());
        System.out.println("------------------------------------------------");
    }
}

public class PayrollSystem05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<Employee> employees = new ArrayList<>();

        System.out.print("Enter number of employees: ");
        int count = sc.nextInt();

        for (int i = 0; i < count; i++) {
            System.out.println("\nEnter details for employee " + (i + 1));
            System.out.print("ID: ");
            int id = sc.nextInt();
            sc.nextLine(); // consume newline
            System.out.print("Name: ");
            String name = sc.nextLine();
            System.out.print("Hourly Rate: ");
            double rate = sc.nextDouble();
            System.out.print("Hours Worked: ");
            int hours = sc.nextInt();
            System.out.print("Bonus: ");
            double bonus = sc.nextDouble();

            employees.add(new Employee(id, name, rate, hours, bonus));
        }

        System.out.println("\n===== PAY SLIPS =====");
        for (Employee emp : employees) {
            emp.displayPaySlip();
        }
    }
}

