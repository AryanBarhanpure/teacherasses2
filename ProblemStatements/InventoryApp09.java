import java.time.LocalDate;
import java.util.*;

class InventoryItem {
    private String name;
    private int quantity;
    private LocalDate expiryDate;

    public InventoryItem(String name, int quantity, LocalDate expiryDate) {
        this.name = name;
        this.quantity = quantity;
        this.expiryDate = expiryDate;
    }

    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void addStock(int amount) {
        this.quantity += amount;
    }

    public void reduceStock(int amount) {
        if (quantity >= amount) {
            this.quantity -= amount;
        } else {
            System.out.println("Not enough stock to reduce.");
        }
    }

    public boolean isExpired() {
        return expiryDate.isBefore(LocalDate.now());
    }

    public boolean needsRestocking() {
        return quantity < 5;
    }

    @Override
    public String toString() {
        return name + " | Qty: " + quantity + " | Expiry: " + expiryDate +
                (isExpired() ? " ❌Expired" : "") +
                (needsRestocking() ? " ⚠️Restock Needed" : "");
    }
}

class InventorySystem {
    private List<InventoryItem> items;

    public InventorySystem() {
        items = new ArrayList<>();
    }

    public void addItem(String name, int quantity, LocalDate expiry) {
        items.add(new InventoryItem(name, quantity, expiry));
    }

    public void showAllItems() {
        System.out.println("\n📦 Inventory Items:");
        for (InventoryItem item : items) {
            System.out.println(item);
        }
    }

    public void restockItem(String name, int quantity) {
        for (InventoryItem item : items) {
            if (item.getName().equalsIgnoreCase(name)) {
                item.addStock(quantity);
                System.out.println("✅ Restocked " + name + " with " + quantity + " units.");
                return;
            }
        }
        System.out.println("❌ Item not found.");
    }

    public void removeExpiredItems() {
        items.removeIf(InventoryItem::isExpired);
        System.out.println("✅ Expired items removed.");
    }
}

public class InventoryApp09 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        InventorySystem system = new InventorySystem();

        system.addItem("Milk", 3, LocalDate.of(2025, 4, 20));
        system.addItem("Rice", 10, LocalDate.of(2026, 1, 1));
        system.addItem("Bread", 2, LocalDate.of(2025, 4, 15)); // assume today is 2025-04-18

        system.showAllItems();

        System.out.print("\nEnter item to restock: ");
        String itemName = sc.nextLine();
        System.out.print("Enter quantity to add: ");
        int qty = sc.nextInt();

        system.restockItem(itemName, qty);

        System.out.println("\nRemoving expired items...");
        system.removeExpiredItems();

        system.showAllItems();
    }
}
