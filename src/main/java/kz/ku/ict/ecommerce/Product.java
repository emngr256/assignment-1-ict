package kz.ku.ict.ecommerce;

/** Base type for sellable catalogue items. */
public abstract class Product {
    private final int id;
    private String name;
    private double price;
    private int stockQuantity;

    protected Product(int id, String name, double price, int stockQuantity) {
        if (id <= 0) throw new IllegalArgumentException("Product ID must be positive.");
        this.id = id;
        setName(name);
        setPrice(price);
        setStockQuantity(stockQuantity);
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Product name is required.");
        this.name = name.trim();
    }
    public double getPrice() { return price; }
    public void setPrice(double price) {
        if (!Double.isFinite(price) || price < 0) throw new IllegalArgumentException("Price cannot be negative.");
        this.price = price;
    }
    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) throw new IllegalArgumentException("Stock cannot be negative.");
        this.stockQuantity = stockQuantity;
    }
    public void reduceStock(int quantity) {
        if (quantity <= 0 || quantity > stockQuantity) throw new IllegalArgumentException("Insufficient stock.");
        stockQuantity -= quantity;
    }
    public abstract String getCategory();
    protected abstract String getDetails();
    public String displayInfo() {
        return String.format("#%d | %-24s | %-12s | $%.2f | stock: %d | %s", id, name, getCategory(), price, stockQuantity, getDetails());
    }
}
