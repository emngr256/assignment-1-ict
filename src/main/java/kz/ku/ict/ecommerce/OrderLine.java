package kz.ku.ict.ecommerce;

/** Immutable product/quantity snapshot stored inside an order. */
public final class OrderLine {
    private final Product product;
    private final int quantity;
    private final double unitPrice;
    public OrderLine(Product product, int quantity) {
        if (product == null || quantity <= 0) throw new IllegalArgumentException("A product and positive quantity are required.");
        this.product = product; this.quantity = quantity; this.unitPrice = product.getPrice();
    }
    public Product getProduct() { return product; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getLineTotal() { return unitPrice * quantity; }
}
