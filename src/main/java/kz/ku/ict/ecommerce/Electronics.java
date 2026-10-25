package kz.ku.ict.ecommerce;

public class Electronics extends Product {
    private int warrantyMonths;
    public Electronics(int id, String name, double price, int stock, int warrantyMonths) {
        super(id, name, price, stock); setWarrantyMonths(warrantyMonths);
    }
    public int getWarrantyMonths() { return warrantyMonths; }
    public void setWarrantyMonths(int months) { if (months < 0) throw new IllegalArgumentException("Warranty cannot be negative."); warrantyMonths = months; }
    @Override public String getCategory() { return "Electronics"; }
    @Override protected String getDetails() { return warrantyMonths + " month warranty"; }
}
