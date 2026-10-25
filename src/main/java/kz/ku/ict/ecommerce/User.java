package kz.ku.ict.ecommerce;

/** Common, encapsulated identity and contact details for system users. */
public abstract class User {
    private final int id;
    private String name;
    private String email;
    private String password;

    protected User(int id, String name, String email, String password) {
        if (id <= 0) throw new IllegalArgumentException("User ID must be positive.");
        this.id = id;
        setName(name);
        setEmail(email);
        setPassword(password);
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public void setName(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name is required.");
        this.name = name.trim();
    }
    public String getEmail() { return email; }
    public void setEmail(String email) {
        if (email == null || !email.matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$"))
            throw new IllegalArgumentException("Enter a valid email address.");
        this.email = email.trim().toLowerCase();
    }
    public String getPassword() { return password; }
    public void setPassword(String password) {
        if (password == null || password.length() < 4) throw new IllegalArgumentException("Password must contain at least 4 characters.");
        this.password = password;
    }
    public abstract String getRole();
    @Override public String toString() { return "#" + id + " " + name + " <" + email + "> - " + getRole(); }
}
