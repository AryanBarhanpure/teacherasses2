import java.util.*;

enum VehicleType {
    CAR, BIKE, TRUCK
}

class Vehicle {
    String plateNumber;
    VehicleType type;

    Vehicle(String plateNumber, VehicleType type) {
        this.plateNumber = plateNumber;
        this.type = type;
    }
}

class Ticket {
    String ticketId;
    String plateNumber;
    long entryTime;

    Ticket(String plateNumber) {
        this.ticketId = UUID.randomUUID().toString();
        this.plateNumber = plateNumber;
        this.entryTime = System.currentTimeMillis();
    }
}

class ParkingLot {
    int carSlots, bikeSlots, truckSlots;
    Map<String, Ticket> activeTickets;

    ParkingLot(int carSlots, int bikeSlots, int truckSlots) {
        this.carSlots = carSlots;
        this.bikeSlots = bikeSlots;
        this.truckSlots = truckSlots;
        this.activeTickets = new HashMap<>();
    }

    public String parkVehicle(Vehicle vehicle) {
        switch (vehicle.type) {
            case CAR:
                if (carSlots <= 0) return "No CAR slots available!";
                carSlots--;
                break;
            case BIKE:
                if (bikeSlots <= 0) return "No BIKE slots available!";
                bikeSlots--;
                break;
            case TRUCK:
                if (truckSlots <= 0) return "No TRUCK slots available!";
                truckSlots--;
                break;
        }

        Ticket ticket = new Ticket(vehicle.plateNumber);
        activeTickets.put(ticket.ticketId, ticket);
        return "Ticket ID: " + ticket.ticketId;
    }

    public String exitVehicle(String ticketId) {
        if (!activeTickets.containsKey(ticketId)) return "Invalid Ticket ID!";
        Ticket ticket = activeTickets.remove(ticketId);

        long duration = (System.currentTimeMillis() - ticket.entryTime) / 1000; // in seconds
        int charge = (int) duration * 1; // ₹1 per second (for example)

        // Simulate freeing slot
        VehicleType type = getVehicleTypeByPlate(ticket.plateNumber);
        if (type == VehicleType.CAR) carSlots++;
        else if (type == VehicleType.BIKE) bikeSlots++;
        else if (type == VehicleType.TRUCK) truckSlots++;

        return "Exited Vehicle [" + ticket.plateNumber + "]. Time Parked: " + duration + "s. Charge: ₹" + charge;
    }

    private VehicleType getVehicleTypeByPlate(String plate) {
        // Mock function – In real case, you’d maintain plate->vehicleType map
        if (plate.startsWith("C")) return VehicleType.CAR;
        if (plate.startsWith("B")) return VehicleType.BIKE;
        return VehicleType.TRUCK;
    }

    public void showAvailability() {
        System.out.println("Available - Cars: " + carSlots + ", Bikes: " + bikeSlots + ", Trucks: " + truckSlots);
    }
}

public class ParkingLotSystem {
    public static void main(String[] args) {
        ParkingLot lot = new ParkingLot(2, 2, 1);
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n1. Park Vehicle\n2. Exit Vehicle\n3. Show Availability\n4. Exit App");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            if (choice == 1) {
                System.out.print("Enter plate number: ");
                String plate = sc.nextLine();
                System.out.print("Enter vehicle type (CAR/BIKE/TRUCK): ");
                String typeStr = sc.nextLine();
                VehicleType type = VehicleType.valueOf(typeStr.toUpperCase());

                Vehicle v = new Vehicle(plate, type);
                System.out.println(lot.parkVehicle(v));

            } else if (choice == 2) {
                System.out.print("Enter ticket ID: ");
                String ticketId = sc.nextLine();
                System.out.println(lot.exitVehicle(ticketId));

            } else if (choice == 3) {
                lot.showAvailability();

            } else {
                System.out.println("Goodbye!");
                break;
            }
        }

        sc.close();
    }
}

