import java.util.Scanner;

class LoanApplicant {
    private double salary;
    private int creditScore;
    private double loanAmount;

    public LoanApplicant(double salary, int creditScore, double loanAmount) {
        this.salary = salary;
        this.creditScore = creditScore;
        this.loanAmount = loanAmount;
    }

    public double getSalary() {
        return salary;
    }

    public int getCreditScore() {
        return creditScore;
    }

    public double getLoanAmount() {
        return loanAmount;
    }
}

class LoanEligibilityChecker {
    public boolean checkEligibility(LoanApplicant applicant) {
        double salary = applicant.getSalary();
        int creditScore = applicant.getCreditScore();
        double loanAmount = applicant.getLoanAmount();

        // Check eligibility based on criteria
        if (salary >= 50000 && creditScore >= 700 && loanAmount <= 5 * salary) {
            return true;  // Eligible for loan
        } else {
            return false; // Not eligible
        }
    }

    public void displayEligibilityResult(boolean isEligible) {
        if (isEligible) {
            System.out.println("✅ Congratulations! You are eligible for the loan.");
        } else {
            System.out.println("❌ Sorry, you are not eligible for the loan.");
        }
    }
}

public class BankLoanEligibilityApp18 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // User input
        System.out.print("Enter your salary: $");
        double salary = sc.nextDouble();

        System.out.print("Enter your credit score: ");
        int creditScore = sc.nextInt();

        System.out.print("Enter the loan amount you wish to apply for: $");
        double loanAmount = sc.nextDouble();

        // Create LoanApplicant object
        LoanApplicant applicant = new LoanApplicant(salary, creditScore, loanAmount);

        // Check loan eligibility
        LoanEligibilityChecker eligibilityChecker = new LoanEligibilityChecker();
        boolean isEligible = eligibilityChecker.checkEligibility(applicant);

        // Display result
        eligibilityChecker.displayEligibilityResult(isEligible);
    }
}

