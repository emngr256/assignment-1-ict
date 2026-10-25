package kz.ku.ict.ecommerce;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** Completed purchase with captured unit prices and a printable receipt. */
public class Order {
    private final int orderId;
    private final Customer customer;
    private final List<OrderLine> items;
    private final LocalDateTime createdAt;
    public Order(int orderId, Customer customer, List<OrderLine> items) {
        if (orderId <= 0 || customer == null || items == null || items.isEmpty()) throw new IllegalArgumentException("A valid order requires a customer and items.");
        this.orderId = orderId; this.customer = customer; this.items = Collections.unmodifiableList(new ArrayList<>(items)); this.createdAt = LocalDateTime.now();
    }
    public int getOrderId() { return orderId; }
    public Customer getCustomer() { return customer; }
    public List<OrderLine> getItems() { return items; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public double getTotalAmount() { return items.stream().mapToDouble(OrderLine::getLineTotal).sum(); }
    public String printReceipt() {
        StringBuilder receipt = new StringBuilder("\n========== ORDER #" + orderId + " ==========\nCustomer: " + customer.getName() + "\nDate: " + createdAt.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")) + "\n");
        for (OrderLine item : items) receipt.append(String.format("%-24s x%-3d $%7.2f each  $%8.2f%n", item.getProduct().getName(), item.getQuantity(), item.getUnitPrice(), item.getLineTotal()));
        return receipt.append(String.format("----------------------------------%nTOTAL: $%.2f%n==================================", getTotalAmount())).toString();
    }
}
