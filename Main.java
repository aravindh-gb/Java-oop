
import java.util.Scanner;

interface ProductActions {
    void displayDetails();
}

abstract class Electronics implements ProductActions {
    private String name;
    private double price;

    Electronics(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    abstract String getCategory();
}

class Mobile extends Electronics {
    private String storage;

    Mobile(String name, double price, String storage) {
        super(name, price);
        this.storage = storage;
    }

    String getCategory() {
        return "Mobile";
    }

    public void displayDetails() {
        System.out.println("Product: " + getName());
        System.out.println("Storage: " + storage);
        System.out.println("Price: Rs. " + getPrice());
    }
}

class Laptop extends Electronics {
    private String processor;

    Laptop(String name, double price, String processor) {
        super(name, price);
        this.processor = processor;
    }

    String getCategory() {
        return "Laptop";
    }

    public void displayDetails() {
        System.out.println("Product: " + getName());
        System.out.println("Processor: " + processor);
        System.out.println("Price: Rs. " + getPrice());
    }
}

class Television extends Electronics {
    private String screenSize;

    Television(String name, double price, String screenSize) {
        super(name, price);
        this.screenSize = screenSize;
    }

    String getCategory() {
        return "Television";
    }

    public void displayDetails() {
        System.out.println("Product: " + getName());
        System.out.println("Screen: " + screenSize);
        System.out.println("Price: Rs. " + getPrice());
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Electronics[] products = {
            new Mobile("Samsung Galaxy S25 Ultra", 129999, "256 GB"),
            new Mobile("iPhone 17 Pro", 134900, "256 GB"),
            new Laptop("MacBook Air M4", 99900, "Apple M4"),
            new Laptop("Dell Inspiron", 65000, "Intel Core i5"),
            new Television("Samsung Smart TV", 45000, "55 inches"),
            new Television("LG OLED TV", 85000, "65 inches")
        };

        int[] quantities = new int[products.length];
        boolean shopping = true;

        while (shopping) {
            System.out.println("\n===== ELECTRONICS STORE =====");
            System.out.println("1. Mobiles");
            System.out.println("2. Laptops");
            System.out.println("3. Televisions");
            System.out.println("4. Checkout and Exit");
            System.out.print("Select category: ");

            int categoryChoice = sc.nextInt();

            if (categoryChoice == 4) {
                break;
            }

            String category;

            switch (categoryChoice) {
                case 1:
                    category = "Mobile";
                    break;
                case 2:
                    category = "Laptop";
                    break;
                case 3:
                    category = "Television";
                    break;
                default:
                    System.out.println("Invalid category!");
                    continue;
            }

            int[] matchingIndexes = new int[products.length];
            int count = 0;

            System.out.println("\n--- " + category + " PRODUCTS ---");

            for (int i = 0; i < products.length; i++) {
                if (products[i].getCategory().equals(category)) {
                    matchingIndexes[count] = i;
                    count++;

                    System.out.println(count + ". "
                            + products[i].getName()
                            + " - Rs. " + products[i].getPrice());
                }
            }

            System.out.print("Select product number: ");
            int productChoice = sc.nextInt();

            if (productChoice < 1 || productChoice > count) {
                System.out.println("Invalid product selection!");
                continue;
            }

            int selectedIndex = matchingIndexes[productChoice - 1];

            System.out.println("\n--- SELECTED PRODUCT ---");
            products[selectedIndex].displayDetails();

            System.out.print("Enter quantity: ");
            int quantity = sc.nextInt();

            if (quantity <= 0) {
                System.out.println("Quantity must be greater than zero!");
                continue;
            }

            quantities[selectedIndex] += quantity;

            double itemTotal =
                    products[selectedIndex].getPrice() * quantity;

            System.out.println("Added to cart successfully!");
            System.out.println("Item total: Rs. " + itemTotal);

            System.out.println("\n1. Add another product");
            System.out.println("2. Checkout and Exit");
            System.out.print("Enter choice: ");

            int nextChoice = sc.nextInt();

            if (nextChoice == 2) {
                shopping = false;
            } else if (nextChoice != 1) {
                System.out.println("Invalid choice. Continuing shopping.");
            }
        }

        double grandTotal = 0;

        System.out.println("\n===== FINAL BILL =====");

        for (int i = 0; i < products.length; i++) {
            if (quantities[i] > 0) {
                double itemTotal =
                        products[i].getPrice() * quantities[i];

                System.out.println(products[i].getName());
                System.out.println("Quantity: " + quantities[i]);
                System.out.println("Subtotal: Rs. " + itemTotal);

                grandTotal += itemTotal;
            }
        }

        System.out.println("-------------------------");
        System.out.println("Grand Total: Rs. " + grandTotal);
        System.out.println("Thank you for shopping!");
        sc.close();
    }
}
