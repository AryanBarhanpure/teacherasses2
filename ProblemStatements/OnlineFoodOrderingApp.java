import java.util.*;

enum DeliveryStatus {
    ORDERED, PREPARING, OUT_FOR_DELIVERY, DELIVERED
}

class FoodItem {
    String id;
    String name;
    double price;

    FoodItem(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return id + " - " + name + " ₹" + price;
    }
}

class CartItem {
    foodItem item;
    int quantity;

    CartItem(foodItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    double getTotalPrice() {
        return item.price * quantity;
    }

    @Override
    public String toString() {
        return item.name + " x" + quantity + " = ₹" + getTotalPrice();
    }
}

class Order {
    String orderId;
    List<cartItem> items;
    deliveryStatus status;

    Order(List<cartItem> cartItems) {
        this.orderId = UUID.randomUUID().toString().substring(0, 8);
        this.items = new ArrayList<>(cartItems);
        this.status = deliveryStatus.ORDERED;
    }

    double getTotal() {
        return items.stream().mapToDouble(cartItem::getTotalPrice).sum();
    }

    void updateStatus(deliveryStatus newStatus) {
        this.status = newStatus;
    }

    @Override
    public String toString() {
        return "Order ID: " + orderId + " | Total: ₹" + getTotal() + " | Status: " + status;
    }
}

class FoodOrderingSystem {
    Map<String, foodItem> menu = new HashMap<>();
    List<cartItem> cart = new ArrayList<>();
    Map<String, Order> orderHistory = new HashMap<>();

    public void loadMenu() {
        menu.put("F001", new foodItem("F001", "Burger", 120));
        menu.put("F002", new foodItem("F002", "Pizza", 250));
        menu.put("F003", new foodItem("F003", "Pasta", 180));
        menu.put("F004", new foodItem("F004", "Fries", 90));
    }

    public void showMenu() {
        System.out.println("\n📋 Menu:");
        for (foodItem item : menu.values()) {
            System.out.println(item);
        }
    }

    public void addToCart(String itemId, int qty) {
        foodItem item = menu.get(itemId);
        if (item == null) {
            System.out.println("Invalid Item ID.");
            return;
        }

        for (cartItem cartItem : cart) {
            if (cartItem.item.id.equals(itemId)) {
                cartItem.quantity += qty;
                System.out.println("Updated quantity for " + item.name);
                return;
            }
        }

        cart.add(new cartItem(item, qty));
        System.out.println("Added to cart: " + item.name);
    }

    public void viewCart() {
        System.out.println("\n🛒 Your Cart:");
        if (cart.isEmpty()) {
            System.out.println("Cart is empty.");
            return;
        }

        double total = 0;
        for (cartItem item : cart) {
            System.out.println(item);
            total += item.getTotalPrice();
        }
        System.out.println("Total: ₹" + total);
    }

    public void placeOrder() {
        if (cart.isEmpty()) {
            System.out.println("Cart is empty. Add items before placing an order.");
            return;
        }

        Order order = new Order(cart);
        orderHistory.put(order.orderId, order);
        cart.clear();
        System.out.println("✅ Order placed! Your Order ID: " + order.orderId);
    }

    public void trackOrder(String orderId) {
        Order order = orderHistory.get(orderId);
        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("\n🔎 Order Tracking:");
        System.out.println(order);
    }

    public void simulateStatusUpdate(String orderId, deliveryStatus newStatus) {
        Order order = orderHistory.get(orderId);
        if (order != null) {
            order.updateStatus(newStatus);
        }
    }
}

public class OnlineFoodOrderingApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        FoodOrderingSystem system = new FoodOrderingSystem();
        system.loadMenu();

        while (true) {
            System.out.println("\n1. View Menu\n2. Add to Cart\n3. View Cart\n4. Place Order\n5. Track Order\n6. Exit");
            int choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    system.showMenu();
                    break;
                case 2:
                    System.out.print("Enter Food ID: ");
                    String id = sc.nextLine();
                    System.out.print("Enter Quantity: ");
                    int qty = sc.nextInt();
                    system.addToCart(id, qty);
                    break;
                case 3:
                    system.viewCart();
                    break;
                case 4:
                    system.placeOrder();
                    break;
                case 5:
                    System.out.print("Enter Order ID: ");
                    String oid = sc.nextLine();
                    system.trackOrder(oid);
                    break;
                case 6:
                    System.out.println("🍽️ Thank you for using the Food Ordering System!");
                    return;
                default:
                    System.out.println("Invalid choice.");
            }
        }
    }
}

