import java.util.*;

class Flight {
    String flightId;
    String origin;
    String destination;
    int totalSeats;
    int availableSeats;

    Flight(String flightId, String origin, String destination, int totalSeats) {
        this.flightId = flightId;
        this.origin = origin;
        this.destination = destination;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
    }

    public boolean bookSeat() {
        if (availableSeats > 0) {
            availableSeats--;
            return true;
        }
        return false;
    }

    public void cancelSeat() {
        if (availableSeats < totalSeats) {
            availableSeats++;
        }
    }

    @Override
    public String toString() {
        return flightId + ": " + origin + " -> " + destination + " | Available Seats: " + availableSeats;
    }
}

class Booking {
    String bookingId;
    String passengerName;
    Flight flight;

    Booking(String passengerName, Flight flight) {
        this.bookingId = UUID.randomUUID().toString().substring(0, 8);
        this.passengerName = passengerName;
        this.flight = flight;
    }

    @Override
    public String toString() {
        return "Booking ID: " + bookingId + ", Name: " + passengerName + ", Flight: " + flight.flightId;
    }
}

class FlightReservationSystem {
    List<Flight> flights = new ArrayList<>();
    Map<String, Booking> bookings = new HashMap<>();

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void showFlights() {
        System.out.println("\nAvailable Flights:");
        for (Flight f : flights) {
            System.out.println(f);
        }
    }

    public void bookFlight(String flightId, String passengerName) {
        for (Flight f : flights) {
            if (f.flightId.equalsIgnoreCase(flightId)) {
                if (f.bookSeat()) {
                    Booking booking = new Booking(passengerName, f);
                    bookings.put(booking.bookingId, booking);
                    System.out.println("Booking successful! " + booking);
                    return;
                } else {
                    System.out.println("No seats available on this flight.");
                    return;
                }
            }
        }
        System.out.println("Flight not found.");
    }

    public void cancelBooking(String bookingId) {
        if (!bookings.containsKey(bookingId)) {
            System.out.println("Booking ID not found.");
            return;
        }
        Booking b = bookings.remove(bookingId);
        b.flight.cancelSeat();
        System.out.println("Booking cancelled: " + bookingId);
    }

    public void showBookings() {
        System.out.println("\nYour Bookings:");
        if (bookings.isEmpty()) {
            System.out.println("No bookings found.");
        } else {
            for (Booking b : bookings.values()) {
                System.out.println(b);
            }
        }
    }
}

public class FlightReservationApp04 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FlightReservationSystem system = new FlightReservationSystem();

        // Adding dummy flights
        system.addFlight(new Flight("F101", "Delhi", "Mumbai", 3));
        system.addFlight(new Flight("F102", "Bangalore", "Chennai", 2));
        system.addFlight(new Flight("F103", "Kolkata", "Hyderabad", 4));

        while (true) {
            System.out.println("\n1. Show Flights\n2. Book Flight\n3. Cancel Booking\n4. Show Bookings\n5. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                system.showFlights();
            } else if (choice == 2) {
                System.out.print("Enter Flight ID: ");
                String flightId = sc.nextLine();
                System.out.print("Enter Your Name: ");
                String name = sc.nextLine();
                system.bookFlight(flightId, name);
            } else if (choice == 3) {
                System.out.print("Enter Booking ID to cancel: ");
                String bookingId = sc.nextLine();
                system.cancelBooking(bookingId);
            } else if (choice == 4) {
                system.showBookings();
            } else {
                System.out.println("Thank you for Booking Flight");
                break;
            }
        }

        sc.close();
    }
}

