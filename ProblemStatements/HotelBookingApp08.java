import java.time.LocalDate;
import java.util.*;

class Room {
    private int roomNumber;
    private String type;
    private List<RoomBooking> bookings;

    public Room(int roomNumber, String type) {
        this.roomNumber = roomNumber;
        this.type = type;
        this.bookings = new ArrayList<>();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getType() {
        return type;
    }

    public boolean isAvailable(LocalDate checkIn, LocalDate checkOut) {
        for (RoomBooking booking : bookings) {
            if (checkIn.isBefore(booking.getCheckOut()) && checkOut.isAfter(booking.getCheckIn())) {
                return false; // Overlapping booking
            }
        }
        return true;
    }

    public void addBooking(RoomBooking booking) {
        bookings.add(booking);
    }

    @Override
    public String toString() {
        return "Room " + roomNumber + " (" + type + ")";
    }
}

class RoomBooking {
    private String customerName;
    private Room room;
    private LocalDate checkIn;
    private LocalDate checkOut;

    public RoomBooking(String customerName, Room room, LocalDate checkIn, LocalDate checkOut) {
        this.customerName = customerName;
        this.room = room;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void printBooking() {
        System.out.println("Booking for " + customerName);
        System.out.println("Room: " + room);
        System.out.println("Check-in: " + checkIn);
        System.out.println("Check-out: " + checkOut);
    }
}

class HotelSystem {
    private List<Room> rooms;

    public HotelSystem() {
        rooms = new ArrayList<>();
        rooms.add(new Room(101, "Single"));
        rooms.add(new Room(102, "Double"));
        rooms.add(new Room(103, "Suite"));
    }

    public void showAvailableRooms(LocalDate checkIn, LocalDate checkOut) {
        System.out.println("\n🛏 Available Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable(checkIn, checkOut)) {
                System.out.println(room);
            }
        }
    }

    public boolean bookRoom(String customerName, int roomNumber, LocalDate checkIn, LocalDate checkOut) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber && room.isAvailable(checkIn, checkOut)) {
                RoomBooking booking = new RoomBooking(customerName, room, checkIn, checkOut);
                room.addBooking(booking);
                System.out.println("\n✅ Booking Successful!");
                booking.printBooking();
                return true;
            }
        }
        System.out.println("❌ Room not available for the selected dates.");
        return false;
    }
}

public class HotelBookingApp08 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        HotelSystem hotel = new HotelSystem();

        System.out.print("Enter customer name: ");
        String name = sc.nextLine();

        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        LocalDate checkIn = LocalDate.parse(sc.nextLine());

        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        LocalDate checkOut = LocalDate.parse(sc.nextLine());

        hotel.showAvailableRooms(checkIn, checkOut);

        System.out.print("Enter room number to book: ");
        int roomNo = sc.nextInt();

        hotel.bookRoom(name, roomNo, checkIn, checkOut);
    }
}

