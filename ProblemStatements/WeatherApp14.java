import java.util.*;

class WeatherForecast {
    private String city;
    private String[] days = {"Day 1", "Day 2", "Day 3", "Day 4", "Day 5"};
    private double[] temperatures = {28.5, 30.2, 29.8, 31.0, 32.5}; // Temperatures in Celsius
    private String[] conditions = {"Sunny", "Partly Cloudy", "Rainy", "Cloudy", "Sunny"};

    public WeatherForecast(String city) {
        this.city = city;
    }

    public void displayForecast() {
        System.out.println("\nWeather forecast for " + city + ":");
        for (int i = 0; i < days.length; i++) {
            System.out.println(days[i] + ": " + temperatures[i] + "°C, " + conditions[i]);
        }
    }
}

public class WeatherApp14 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Take city input from user
        System.out.print("Enter city name: ");
        String city = sc.nextLine();

        // Create WeatherForecast object for the given city
        WeatherForecast forecast = new WeatherForecast(city);

        // Display the weather forecast
        forecast.displayForecast();
    }
}

