import java.util.*;

class CurrencyConverter {
    private Map<String, Double> exchangeRates;

    public CurrencyConverter() {
        exchangeRates = new HashMap<>();
        // Mock exchange rates (1 USD to other currencies)
        exchangeRates.put("USD", 1.0);
        exchangeRates.put("EUR", 0.85);
        exchangeRates.put("GBP", 0.75);
        exchangeRates.put("INR", 74.5);
        exchangeRates.put("JPY", 110.6);
    }

    public double convertCurrency(String fromCurrency, String toCurrency, double amount) {
        if (!exchangeRates.containsKey(fromCurrency) || !exchangeRates.containsKey(toCurrency)) {
            System.out.println("❌ Unsupported currency.");
            return -1;
        }

        // Convert amount to USD first, then to the target currency
        double usdAmount = amount / exchangeRates.get(fromCurrency);
        double convertedAmount = usdAmount * exchangeRates.get(toCurrency);
        return convertedAmount;
    }

    public void displaySupportedCurrencies() {
        System.out.println("\nSupported currencies:");
        for (String currency : exchangeRates.keySet()) {
            System.out.println(currency);
        }
    }
}

public class CurrencyConverterApp13 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        CurrencyConverter converter = new CurrencyConverter();

        // Display available currencies
        converter.displaySupportedCurrencies();

        // Input the conversion details
        System.out.print("\nEnter the currency to convert from (e.g., USD, EUR, INR): ");
        String fromCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter the currency to convert to (e.g., USD, EUR, INR): ");
        String toCurrency = sc.nextLine().toUpperCase();

        System.out.print("Enter the amount to convert: ");
        double amount = sc.nextDouble();

        // Perform the conversion
        double result = converter.convertCurrency(fromCurrency, toCurrency, amount);

        if (result != -1) {
            System.out.println("\nConverted amount: " + amount + " " + fromCurrency + " = " + result + " " + toCurrency);
        }
    }
}

