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
        System.out.println("\n========== MENU ==========");
        for (int i = 0; i < menu.length; i++) {
            System.out.println((i + 1) + ". " + menu[i].name + " - ₹" + menu[i].price);
        }
        System.out.println("==========================");
    }
}

// Order Class
class Order {
    FoodItem items[] = new FoodItem[10];
    int qty[] = new int[10];
    int count = 0;

    void addItem(FoodItem f, int q) {
        if (count < 10) {
            items[count] = f;
            qty[count] = q;
            count++;
        } else {
            System.out.println("Error: Order list is full! Cannot add more items.");
        }
    }

    void generateBill(String customerName) {
        double subtotal = 0;
        int totalItems = 0;

        // Calculate subtotal and total items
        for (int i = 0; i < count; i++) {
            subtotal += items[i].price * qty[i];
            totalItems += qty[i];
        }

        // Delivery charge
        double delivery;
        if (subtotal > 500) {
            delivery = 0;
        } else {
            delivery = 50;
        }

        // Discount
        double discount = 0;
        if (subtotal >= 1000) {
            discount = subtotal * 0.10;
        }

        // Tax
        double tax = subtotal * 0.05;

        // Final total
        double total = subtotal - discount + delivery + tax;

        // Display Bill
        System.out.println("\n========== ORDER SUMMARY ==========");
        System.out.println("Customer Name: " + customerName);

        for (int i = 0; i < count; i++) {
            System.out.println(items[i].name + " x" + qty[i] +
                    " = ₹" + (items[i].price * qty[i]));
        }

        System.out.println("-----------------------------------");
        System.out.println("Total Items Ordered = " + totalItems);
        System.out.println("Subtotal = ₹" + subtotal);
        System.out.println("Discount = ₹" + discount);
        System.out.println("Delivery Charge = ₹" + delivery);
        System.out.println("Tax (5%) = ₹" + tax);

        if (delivery == 0) {
            System.out.println("Congratulations! You got Free Delivery.");
        }

        System.out.println("Total Amount = ₹" + total);
        System.out.println("===================================");
    }
}

// Main Class
public class Main {
    public static void main(String args[]) {
        Scanner sc = new Scanner(System.in);

        // Customer Name
        System.out.print("Enter Customer Name: ");
        String customerName = sc.nextLine();

        // Menu
        FoodItem m[] = new FoodItem[5];
        m[0] = new FoodItem("Burger", 100);
        m[1] = new FoodItem("Pizza", 300);
        m[2] = new FoodItem("Pasta", 200);
        m[3] = new FoodItem("Sandwich", 120);
        m[4] = new FoodItem("Cold Drink", 50);

        Restaurant r = new Restaurant(m);
        Order o = new Order();

        int choice = -1;

        do {
            r.showMenu();
            System.out.print("Enter choice (0 to stop): ");
            
            try {
                choice = sc.nextInt();

                if (choice >= 1 && choice <= 5) {
                    System.out.print("Enter quantity: ");
                    int q = sc.nextInt();
                    
                    if (q > 0) {
                        o.addItem(m[choice - 1], q);
                        System.out.println("Item added successfully!");
                    } else {
                        System.out.println("Error: Quantity must be greater than 0!");
                    }
                } 
                else if (choice != 0) {
                    System.out.println("Invalid Choice! Please enter a number between 1-5 or 0 to stop.");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("Error: Invalid input! Please enter a valid number.");
                sc.nextLine(); // Clear invalid input
                choice = -1; // Continue loop
            }

        } while (choice != 0);

        // Generate Final Bill
        o.generateBill(customerName);

        sc.close();
    }
}