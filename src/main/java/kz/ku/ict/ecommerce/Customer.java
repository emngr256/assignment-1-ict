package kz.ku.ict.ecommerce;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/** A shopper who can place and review orders. */
public class Customer extends User {
    private final List<Order> orders = new ArrayList<>();
    public Customer(int id, String name, String email, String password) { super(id, name, email, password); }
    @Override public String getRole() { return "Customer"; }
    void addOrder(Order order) { orders.add(order); }
    public List<Order> getOrders() { return Collections.unmodifiableList(orders); }
}
