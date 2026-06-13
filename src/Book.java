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

    public void setTitle(String title) {
        if (!title.trim().isEmpty()) this.title = title;
    }

    public void setAuthor(String author) {
        if (!author.trim().isEmpty()) this.author = author;
    }

    public void setQuantity(int quantity) {
        if (quantity >= 0) {
            this.quantity = quantity;
            if (this.availableQuantity > quantity) this.availableQuantity = quantity;
        }
    }

    public void setCategory(String category) {
        if (!category.trim().isEmpty()) this.category = category;
    }

    public boolean isAvailable() {
        return availableQuantity > 0;
    }

    public boolean borrowBook() {
        if (availableQuantity > 0) {
            availableQuantity--;
            return true;
        }
        return false;
    }

    public void returnBook() {
        if (availableQuantity < quantity) availableQuantity++;
    }

    public void displayInfo() {
        System.out.println("ID: " + id + " | Title: " + title + " | Author: " + author);
        System.out.println("  ISBN: " + isbn + " | Category: " + category);
        System.out.println("  Total: " + quantity + " | Available: " + availableQuantity);
    }
}
