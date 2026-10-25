package kz.ku.ict.ecommerce;

/** An employee allowed to maintain the catalogue and inspect all orders. */
public class Employee extends User {
    private String department;
    public Employee(int id, String name, String email, String password, String department) {
        super(id, name, email, password);
        setDepartment(department);
    }
    @Override public String getRole() { return "Employee"; }
    public String getDepartment() { return department; }
    public void setDepartment(String department) {
        if (department == null || department.isBlank()) throw new IllegalArgumentException("Department is required.");
        this.department = department.trim();
    }
}
