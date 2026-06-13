import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManager {
    private ArrayList<Book> books;
    private Scanner scanner;
    private int nextId;

    public InventoryManager() {
        books = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
        books.add(new Book(nextId++, "The Great Gatsby", "F. Scott Fitzgerald", "9780743273565", 5, "Fiction"));
        books.add(new Book(nextId++, "To Kill a Mockingbird", "Harper Lee", "9780061120084", 3, "Fiction"));
        books.add(new Book(nextId++, "Clean Code", "Robert Martin", "9780132350884", 4, "Technology"));
    }

    public void addBook() {
        System.out.println("\n=== ADD BOOK ===");
        System.out.print("Title: ");
        String title = scanner.nextLine();
        System.out.print("Author: ");
        String author = scanner.nextLine();
        System.out.print("ISBN: ");
        String isbn = scanner.nextLine();
        System.out.print("Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Category: ");
        String category = scanner.nextLine();
        books.add(new Book(nextId++, title, author, isbn, quantity, category));
        System.out.println("✓ Book added! ID: " + (nextId-1));
    }

    public void viewAllBooks() {
        System.out.println("\n=== ALL BOOKS (" + books.size() + ") ===");
        for (Book b : books) {
            b.displayInfo();
            System.out.println("----------------------");
        }
    }

    public void searchById() {
        System.out.print("\nEnter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Book b : books) {
            if (b.getId() == id) {
                b.displayInfo();
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void searchByTitle() {
        System.out.print("\nEnter Title: ");
        String title = scanner.nextLine();
        boolean found = false;
        for (Book b : books) {
            if (b.getTitle().toLowerCase().contains(title.toLowerCase())) {
                b.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No books found!");
    }

    public void searchByAuthor() {
        System.out.print("\nEnter Author: ");
        String author = scanner.nextLine();
        boolean found = false;
        for (Book b : books) {
            if (b.getAuthor().toLowerCase().contains(author.toLowerCase())) {
                b.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No books found!");
    }

    public void updateBook() {
        System.out.print("\nEnter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Book b : books) {
            if (b.getId() == id) {
                System.out.print("New Title (" + b.getTitle() + "): ");
                String title = scanner.nextLine();
                if (!title.isEmpty()) b.setTitle(title);
                System.out.print("New Author (" + b.getAuthor() + "): ");
                String author = scanner.nextLine();
                if (!author.isEmpty()) b.setAuthor(author);
                System.out.print("New Quantity (" + b.getQuantity() + "): ");
                String qty = scanner.nextLine();
                if (!qty.isEmpty()) b.setQuantity(Integer.parseInt(qty));
                System.out.println("✓ Updated!");
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void deleteBook() {
        System.out.print("\nEnter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < books.size(); i++) {
            if (books.get(i).getId() == id) {
                System.out.print("Delete? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    books.remove(i);
                    System.out.println("✓ Deleted!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void checkAvailability() {
        System.out.print("\nEnter Book ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (Book b : books) {
            if (b.getId() == id) {
                if (b.isAvailable()) {
                    System.out.println("✓ Available! (" + b.getAvailableQuantity() + " copies)");
                } else {
                    System.out.println("✗ Not available!");
                }
                return;
            }
        }
        System.out.println("Book not found!");
    }

    public void menu() {
        System.out.println("\n--- INVENTORY MENU ---");
        System.out.println("1. Add Book");
        System.out.println("2. View All Books");
        System.out.println("3. Search by ID");
        System.out.println("4. Search by Title");
        System.out.println("5. Search by Author");
        System.out.println("6. Update Book");
        System.out.println("7. Delete Book");
        System.out.println("8. Check Availability");
        System.out.println("0. Back");
        System.out.print("Choice: ");
    }
}
