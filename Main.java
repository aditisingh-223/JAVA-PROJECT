import java.util.Scanner;

// FoodItem Class
class FoodItem {
    String name;
    double price;

    FoodItem(String n, double p) {
        name = n;
        price = p;
    }
}

// Restaurant Class
class Restaurant {
    FoodItem menu[];

    Restaurant(FoodItem m[]) {
        menu = m;
    }

    void showMenu() {
        System.out.println("\nMenu:");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].name + " - ₹" + menu[i].price);
        }
    }
}

// Order Class
class Order {
    FoodItem items[] = new FoodItem[10];
    int qty[] = new int[10];
    int count = 0;

    void addItem(FoodItem f, int q) {
        items[count] = f;
        qty[count] = q;
        count++;
    }

    void generateBill() {
        double subtotal = 0;

        // Calculate subtotal
        for (int i = 0; i < count; i++) {
            subtotal = subtotal + items[i].price * qty[i];
        }

        // Delivery charge
        double delivery;
        if (subtotal > 500) {
            delivery = 0;
        } else {
            delivery = 50;
        }

        // Tax
        double tax = subtotal * 0.05;

        // Final total
        double total = subtotal + delivery + tax;

        // Display
        System.out.println("\n----- ORDER SUMMARY -----");

        for (int i = 0; i < count; i++) {
            System.out.println(items[i].name + " x" + qty[i] + " = ₹"
                    + (items[i].price * qty[i]));
        }

        System.out.println("--------------------------");
        System.out.println("Subtotal = ₹" + subtotal);
        System.out.println("Delivery Charge = ₹" + delivery);
        System.out.println("Tax (5%) = ₹" + tax);
        System.out.println("Total Amount = ₹" + total);
    }
}

// Main Class
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Menu
        FoodItem m[] = new FoodItem[3];
        m[0] = new FoodItem("Burger", 100);
        m[1] = new FoodItem("Pizza", 300);
        m[2] = new FoodItem("Pasta", 200);

        Restaurant r = new Restaurant(m);
        Order o = new Order();

        int choice;

        do {
            r.showMenu();
            System.out.print("Enter choice (0 to stop): ");
            choice = sc.nextInt();

            if (choice >= 1 && choice <= 3) {
                System.out.print("Enter quantity: ");
                int q = sc.nextInt();
                o.addItem(m[choice - 1], q);
            }

        } while (choice != 0);

        // FINAL BILL
        o.generateBill();
    }
}