import java.util.regex.*;

class PasswordStrengthChecker {

    public boolean checkStrength(String password) {
        // Check length
        if (password.length() < 8) {
            System.out.println("❌ Password is too short. Minimum length is 8 characters.");
            return false;
        }

        // Check for uppercase letter
        if (!Pattern.compile("[A-Z]").matcher(password).find()) {
            System.out.println("❌ Password must contain at least one uppercase letter.");
            return false;
        }

        // Check for lowercase letter
        if (!Pattern.compile("[a-z]").matcher(password).find()) {
            System.out.println("❌ Password must contain at least one lowercase letter.");
            return false;
        }

        // Check for digit
        if (!Pattern.compile("[0-9]").matcher(password).find()) {
            System.out.println("❌ Password must contain at least one digit.");
            return false;
        }

        // Check for special symbol
        if (!Pattern.compile("[!@#$%^&*(),.?\":{}|<>]").matcher(password).find()) {
            System.out.println("❌ Password must contain at least one special character.");
            return false;
        }

        // If all checks pass
        return true;
    }
}

public class PasswordStrengthCheckerApp19 {
    public static void main(String[] args) {
        java.util.Scanner sc = new java.util.Scanner(System.in);

        // User input
        System.out.print("Enter your password: ");
        String password = sc.nextLine();

        // Check password strength
        PasswordStrengthChecker checker = new PasswordStrengthChecker();
        boolean isStrong = checker.checkStrength(password);

        // Display result
        if (isStrong) {
            System.out.println("✅ Password is strong!");
        }
    }
}
