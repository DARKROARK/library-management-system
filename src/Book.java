public class Book {
    private int id;
    private String title;
    private String author;
    private String isbn;
    private int quantity;
    private int availableQuantity;
    private String category;

    public Book(int id, String title, String author, String isbn, int quantity, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.quantity = quantity;
        this.availableQuantity = quantity;
        this.category = category;
    }

    public int getId() { return id; }
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public String getIsbn() { return isbn; }
    public int getQuantity() { return quantity; }
    public int getAvailableQuantity() { return availableQuantity; }
    public String getCategory() { return category; }

    public void setTitle(String title) { this.title = title; }
    public void setAuthor(String author) { this.author = author; }
    public void setCategory(String category) { this.category = category; }
    
    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
            this.availableQuantity = quantity;
        }
    }

    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | " + title + " | " + author);
        System.out.println("  ISBN: " + isbn + " | " + category + " | Available: " + availableQuantity);
    }
}