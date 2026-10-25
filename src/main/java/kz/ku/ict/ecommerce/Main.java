package kz.ku.ict.ecommerce;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/** Console entry point. Demo data is loaded at startup and remains in memory. */
public class Main {
    private final Scanner scanner = new Scanner(System.in);
    private final EcommerceSystem system = new EcommerceSystem();

    public static void main(String[] args) { new Main().run(); }
    private void run() {
        System.out.println("============================================");
        System.out.println("       KU CAMPUS E-COMMERCE DEMO");
        System.out.println("============================================");
        boolean running = true;
        while (running) {
            System.out.println("\nMain Menu\n1. Customer menu\n2. Employee menu\n3. List users\n4. List products\n5. Exit");
            switch (readInt("Choose: ", 1, 5)) {
                case 1 -> customerMenu();
                case 2 -> employeeMenu();
                case 3 -> listUsers();
                case 4 -> listProducts();
                case 5 -> running = false;
            }
        }
        System.out.println("Thank you for visiting. Goodbye!");
    }
    private void customerMenu() {
        List<Customer> customers = system.getCustomers();
        System.out.println("\nSelect customer:");
        for (int i = 0; i < customers.size(); i++) System.out.printf("%d. %s%n", i + 1, customers.get(i).getName());
        int selected = readInt("Customer number (0 to cancel): ", 0, customers.size());
        if (selected == 0) return;
        Customer customer = customers.get(selected - 1);
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nCustomer Menu - " + customer.getName() + "\n1. List products\n2. Place an order\n3. View my orders\n4. Back");
            switch (readInt("Choose: ", 1, 4)) {
                case 1 -> listProducts();
                case 2 -> placeOrder(customer);
                case 3 -> customer.getOrders().forEach(o -> System.out.println(o.printReceipt()));
                case 4 -> inMenu = false;
            }
        }
    }
    private void employeeMenu() {
        boolean inMenu = true;
        while (inMenu) {
            System.out.println("\nEmployee Menu\n1. List products\n2. Add product\n3. Add customer\n4. Add employee\n5. List users\n6. View all orders\n7. Back");
            switch (readInt("Choose: ", 1, 7)) {
                case 1 -> listProducts();
                case 2 -> addProduct();
                case 3 -> addCustomer();
                case 4 -> addEmployee();
                case 5 -> listUsers();
                case 6 -> { if (system.getOrders().isEmpty()) System.out.println("No orders yet."); else system.getOrders().forEach(o -> System.out.println(o.printReceipt())); }
                case 7 -> inMenu = false;
            }
        }
    }
    private void listProducts() {
        System.out.println("\nAvailable products:");
        if (system.getProducts().isEmpty()) { System.out.println("No products in catalogue."); return; }
        system.getProducts().forEach(p -> System.out.println(p.displayInfo()));
    }
    private void listUsers() {
        System.out.println("\nRegistered users:");
        if (system.getUsers().isEmpty()) { System.out.println("No users registered."); return; }
        system.getUsers().forEach(System.out::println);
    }
    private void placeOrder(Customer customer) {
        List<OrderLine> cart = new ArrayList<>();
        while (true) {
            listProducts();
            int id = readInt("Enter product ID (0 to finish): ", 0, Integer.MAX_VALUE);
            if (id == 0) break;
            Product product = system.findProduct(id);
            if (product == null) { System.out.println("Product not found."); continue; }
            if (product.getStockQuantity() == 0) { System.out.println("This product is out of stock."); continue; }
            int quantity = readInt("Quantity (1-" + product.getStockQuantity() + "): ", 1, product.getStockQuantity());
            cart.add(new OrderLine(product, quantity));
            System.out.println("Added to cart.");
        }
        if (cart.isEmpty()) { System.out.println("Order cancelled; cart was empty."); return; }
        try { System.out.println(system.placeOrder(customer, cart).printReceipt()); }
        catch (IllegalArgumentException ex) { System.out.println("Order could not be placed: " + ex.getMessage()); }
    }
    private void addProduct() {
        System.out.println("Product type: 1. Book  2. Electronics  3. Clothing");
        int type = readInt("Choose: ", 1, 3);
        String name = readText("Name: ");
        double price = readDouble("Price: ", 0, 1_000_000);
        int stock = readInt("Initial stock: ", 0, 1_000_000);
        try {
            Product product = switch (type) {
                case 1 -> system.addBook(name, price, stock, readText("Author: "), readText("ISBN: "));
                case 2 -> system.addElectronics(name, price, stock, readInt("Warranty months: ", 0, 120));
                default -> system.addClothing(name, price, stock, readText("Size: "), readText("Material: "));
            };
            System.out.println("Added: " + product.displayInfo());
        } catch (IllegalArgumentException ex) { System.out.println("Could not add product: " + ex.getMessage()); }
    }
    private void addEmployee() {
        try {
            Employee employee = system.addEmployee(readText("Full name: "), readText("Email: "), readText("Password (min 4 chars): "), readText("Department: "));
            System.out.println("Employee created: " + employee);
        } catch (IllegalArgumentException ex) { System.out.println("Could not add employee: " + ex.getMessage()); }
    }
    private void addCustomer() {
        try {
            Customer customer = system.addCustomer(readText("Full name: "), readText("Email: "), readText("Password (min 4 chars): "));
            System.out.println("Customer created: " + customer);
        } catch (IllegalArgumentException ex) { System.out.println("Could not add customer: " + ex.getMessage()); }
    }
    private int readInt(String prompt, int min, int max) {
        while (true) {
            System.out.print(prompt);
            try { int value = Integer.parseInt(scanner.nextLine().trim()); if (value >= min && value <= max) return value; }
            catch (NumberFormatException ignored) { }
            System.out.println("Enter a whole number between " + min + " and " + max + ".");
        }
    }
    private double readDouble(String prompt, double min, double max) {
        while (true) {
            System.out.print(prompt);
            try { double value = Double.parseDouble(scanner.nextLine().trim()); if (Double.isFinite(value) && value >= min && value <= max) return value; }
            catch (NumberFormatException ignored) { }
            System.out.println("Enter a valid number between " + min + " and " + max + ".");
        }
    }
    private String readText(String prompt) {
        while (true) { System.out.print(prompt); String value = scanner.nextLine().trim(); if (!value.isEmpty()) return value; System.out.println("This value cannot be empty."); }
    }
}






