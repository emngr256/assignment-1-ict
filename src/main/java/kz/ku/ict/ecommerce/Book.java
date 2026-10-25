package kz.ku.ict.ecommerce;

public class Book extends Product {
    private String author;
    private String isbn;
    public Book(int id, String name, double price, int stock, String author, String isbn) {
        super(id, name, price, stock); setAuthor(author); setIsbn(isbn);
    }
    public String getAuthor() { return author; }
    public void setAuthor(String author) { if (author == null || author.isBlank()) throw new IllegalArgumentException("Author is required."); this.author = author.trim(); }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { if (isbn == null || isbn.isBlank()) throw new IllegalArgumentException("ISBN is required."); this.isbn = isbn.trim(); }
    @Override public String getCategory() { return "Book"; }
    @Override protected String getDetails() { return "by " + author + ", ISBN " + isbn; }
}
