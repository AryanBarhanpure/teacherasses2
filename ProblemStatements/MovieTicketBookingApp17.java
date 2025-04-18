import java.util.*;

class Movie {
    private String title;
    private List<String> showtimes;

    public Movie(String title, List<String> showtimes) {
        this.title = title;
        this.showtimes = showtimes;
    }

    public String getTitle() {
        return title;
    }

    public List<String> getShowtimes() {
        return showtimes;
    }
}

class Seat {
    private boolean isBooked;

    public Seat() {
        this.isBooked = false;
    }

    public boolean isBooked() {
        return isBooked;
    }

    public void bookSeat() {
        this.isBooked = true;
    }
}

class BookingSystem {
    private List<Movie> movies;
    private Map<String, List<Seat>> seatAvailability;

    public BookingSystem() {
        this.movies = new ArrayList<>();
        this.seatAvailability = new HashMap<>();
    }

    public void addMovie(Movie movie) {
        movies.add(movie);
        seatAvailability.put(movie.getTitle(), new ArrayList<>());
        for (int i = 0; i < 10; i++) {  // 10 seats for simplicity
            seatAvailability.get(movie.getTitle()).add(new Seat());
        }
    }

    public void viewMovies() {
        System.out.println("\nMovies Available:");
        for (Movie movie : movies) {
            System.out.println(movie.getTitle());
            for (String time : movie.getShowtimes()) {
                System.out.println(" - Showtime: " + time);
            }
        }
    }

    public void bookTicket(String movieTitle, String showtime) {
        List<Seat> seats = seatAvailability.get(movieTitle);

        if (seats == null) {
            System.out.println("❌ Movie not found.");
            return;
        }

        boolean isSeatBooked = false;
        for (Seat seat : seats) {
            if (!seat.isBooked()) {
                seat.bookSeat();
                isSeatBooked = true;
                System.out.println("✅ Ticket booked for " + movieTitle + " at " + showtime);
                break;
            }
        }

        if (!isSeatBooked) {
            System.out.println("❌ Sorry, no available seats for this movie at this time.");
        }
    }
}

public class MovieTicketBookingApp17 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BookingSystem bookingSystem = new BookingSystem();

        // Adding movies and showtimes
        List<String> showtimes1 = Arrays.asList("10:00 AM", "1:00 PM", "4:00 PM");
        Movie movie1 = new Movie("Avengers: Endgame", showtimes1);
        bookingSystem.addMovie(movie1);

        List<String> showtimes2 = Arrays.asList("11:00 AM", "2:00 PM", "5:00 PM");
        Movie movie2 = new Movie("Spider-Man: No Way Home", showtimes2);
        bookingSystem.addMovie(movie2);

        // Display available movies and showtimes
        bookingSystem.viewMovies();

        // Booking a ticket
        System.out.print("\nEnter movie title to book a ticket: ");
        String movieTitle = sc.nextLine();

        System.out.print("Enter showtime: ");
        String showtime = sc.nextLine();

        bookingSystem.bookTicket(movieTitle, showtime);
    }
}

