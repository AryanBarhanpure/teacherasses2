import java.util.*;

class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}

class Cart {
    private List<Product> products;

    public Cart() {
        products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        products.add(product);
        System.out.println("✅ " + product.getName() + " added to cart.");
    }

    public void removeProduct(String productName) {
        boolean found = false;
        for (Product product : products) {
            if (product.getName().equalsIgnoreCase(productName)) {
                products.remove(product);
                System.out.println("✅ " + product.getName() + " removed from cart.");
                found = true;
                break;
            }
        }
        if (!found) {
            System.out.println("❌ Product not found in the cart.");
        }
    }

    public double calculateTotal() {
        double total = 0;
        for (Product product : products) {
            total += product.getPrice();
        }
        return total;
    }

    public void viewCart() {
        if (products.isEmpty()) {
            System.out.println("❌ Your cart is empty.");
        } else {
            System.out.println("\n🛒 Your Cart:");
            for (Product product : products) {
                System.out.println(product.getName() + " - $" + product.getPrice());
            }
        }
    }
}

class CartSystem {
    private Cart cart;

    public CartSystem() {
        cart = new Cart();
    }

    public void start() {
        Scanner sc = new Scanner(System.in);
        // Sample products
        Product p1 = new Product("Laptop", 799.99);
        Product p2 = new Product("Smartphone", 599.99);
        Product p3 = new Product("Headphones", 149.99);

        // Adding products to the cart
        cart.addProduct(p1);
        cart.addProduct(p2);
        cart.addProduct(p3);

        // View cart
        cart.viewCart();

        // Remove product
        System.out.print("\nEnter product name to remove from cart: ");
        String removeProductName = sc.nextLine();
        cart.removeProduct(removeProductName);

        // View cart after removal
        cart.viewCart();

        // Checkout (Display total cost)
        double total = cart.calculateTotal();
        System.out.println("\n💰 Total cost: $" + total);
    }
}

public class ECommerceCartApp15 {
    public static void main(String[] args) {
        CartSystem cartSystem = new CartSystem();
        cartSystem.start();
    }
}

