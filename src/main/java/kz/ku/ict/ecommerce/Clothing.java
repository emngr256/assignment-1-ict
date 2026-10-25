package kz.ku.ict.ecommerce;

public class Clothing extends Product {
    private String size;
    private String material;
    public Clothing(int id, String name, double price, int stock, String size, String material) {
        super(id, name, price, stock); setSize(size); setMaterial(material);
    }
    public String getSize() { return size; }
    public void setSize(String size) { if (size == null || size.isBlank()) throw new IllegalArgumentException("Size is required."); this.size = size.trim(); }
    public String getMaterial() { return material; }
    public void setMaterial(String material) { if (material == null || material.isBlank()) throw new IllegalArgumentException("Material is required."); this.material = material.trim(); }
    @Override public String getCategory() { return "Clothing"; }
    @Override protected String getDetails() { return "size " + size + ", " + material; }
}
