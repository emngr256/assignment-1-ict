package kz.ku.ict.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** In-memory application model and operations shared by the console menus. */
public class EcommerceSystem {
    private final List<User> users = new ArrayList<>();
    private final List<Product> products = new ArrayList<>();
    private final List<Order> orders = new ArrayList<>();
    private int nextUserId = 1, nextProductId = 1, nextOrderId = 1;

    public EcommerceSystem() { seedDemoData(); }
    public List<User> getUsers() { return Collections.unmodifiableList(users); }
    public List<Product> getProducts() { return Collections.unmodifiableList(products); }
    public List<Order> getOrders() { return Collections.unmodifiableList(orders); }
    public List<Customer> getCustomers() { return users.stream().filter(Customer.class::isInstance).map(Customer.class::cast).toList(); }
    public Customer addCustomer(String name, String email, String password) { ensureUniqueEmail(email); Customer c = new Customer(nextUserId++, name, email, password); users.add(c); return c; }
    public Employee addEmployee(String name, String email, String password, String department) { ensureUniqueEmail(email); Employee e = new Employee(nextUserId++, name, email, password, department); users.add(e); return e; }
    public Product addBook(String name, double price, int stock, String author, String isbn) { Book p = new Book(nextProductId++, name, price, stock, author, isbn); products.add(p); return p; }
    public Product addElectronics(String name, double price, int stock, int warranty) { Electronics p = new Electronics(nextProductId++, name, price, stock, warranty); products.add(p); return p; }
    public Product addClothing(String name, double price, int stock, String size, String material) { Clothing p = new Clothing(nextProductId++, name, price, stock, size, material); products.add(p); return p; }
    public Product findProduct(int id) { return products.stream().filter(p -> p.getId() == id).findFirst().orElse(null); }
    public Order placeOrder(Customer customer, List<OrderLine> items) {
        if (customer == null || items == null || items.isEmpty()) throw new IllegalArgumentException("Choose a customer and at least one item.");
        java.util.Map<Integer, Integer> requestedQuantities = new java.util.HashMap<>();
        for (OrderLine line : items) requestedQuantities.merge(line.getProduct().getId(), line.getQuantity(), Integer::sum);
        for (var request : requestedQuantities.entrySet()) {
            Product current = findProduct(request.getKey());
            if (current == null || request.getValue() > current.getStockQuantity()) {
                String name = current == null ? "requested product" : current.getName();
                throw new IllegalArgumentException("Insufficient stock for " + name + ".");
            }
        }
        for (var request : requestedQuantities.entrySet()) findProduct(request.getKey()).reduceStock(request.getValue());
        Order order = new Order(nextOrderId++, customer, items); orders.add(order); customer.addOrder(order); return order;
    }
    private void ensureUniqueEmail(String email) {
        if (email == null || users.stream().anyMatch(u -> u.getEmail().equalsIgnoreCase(email.trim()))) throw new IllegalArgumentException("Email address is already registered or invalid.");
    }
    private void seedDemoData() {
        addCustomer("Amina Sadyk", "amina@example.com", "demo123");
        addCustomer("Daniyar Omar", "daniyar@example.com", "demo123");
        addEmployee("Store Manager", "manager@example.com", "demo123", "Sales");
        addBook("Clean Code", 32.50, 8, "Robert C. Martin", "9780132350884");
        addElectronics("Wireless Mouse", 18.99, 12, 24);
        addClothing("Campus Hoodie", 29.00, 6, "M", "Cotton");
    }
}

