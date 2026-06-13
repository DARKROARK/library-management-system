import java.util.ArrayList;
import java.util.Date;
import java.util.Calendar;
import java.util.Scanner;

public class TransactionManager {
    private ArrayList<Transaction> transactions;
    private Scanner scanner;
    private int nextTransactionId;
    private UserManager userManager;
    private InventoryManager inventoryManager;

    public TransactionManager(UserManager userManager, InventoryManager inventoryManager) {
        this.transactions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.nextTransactionId = 1;
        this.userManager = userManager;
        this.inventoryManager = inventoryManager;
        addSampleTransactions();
    }

    private void addSampleTransactions() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -5);
        Date pastDate = cal.getTime();
        cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 9);
        Date futureDate = cal.getTime();
        
        transactions.add(new Transaction(nextTransactionId++, 1, "Alice Khan", 
                         1, "The Great Gatsby", pastDate, futureDate));
    }

    // 1. Borrow Book
    public void borrowBook() {
        System.out.println("\n=== BORROW BOOK ===");
        
        System.out.print("Enter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        User user = userManager.getUserById(userId);
        if (user == null) {
            System.out.println("✗ User not found!");
            return;
        }
        
        System.out.print("Enter Book ID: ");
        int bookId = scanner.nextInt();
        scanner.nextLine();
        
        Book book = inventoryManager.getBookById(bookId);
        if (book == null) {
            System.out.println("✗ Book not found!");
            return;
        }
        
        if (!book.isAvailable()) {
            System.out.println("✗ Book is not available!");
            return;
        }
        
        // Calculate due date (14 days from now)
        Calendar cal = Calendar.getInstance();
        Date borrowDate = new Date();
        cal.setTime(borrowDate);
        cal.add(Calendar.DAY_OF_MONTH, 14);
        Date dueDate = cal.getTime();
        
        // Update book availability
        if (inventoryManager.updateBookAvailability(bookId, true)) {
            Transaction transaction = new Transaction(nextTransactionId++, userId, 
                user.getName(), bookId, book.getTitle(), borrowDate, dueDate);
            transactions.add(transaction);
            System.out.println("✓ Book borrowed successfully!");
            System.out.println("  Transaction ID: " + transaction.getTransactionId());
            System.out.println("  Return by: " + transaction.getFormattedDueDate());
        } else {
            System.out.println("✗ Failed to borrow book!");
        }
    }

    // 2. Return Book
    public void returnBook() {
        System.out.println("\n=== RETURN BOOK ===");
        
        System.out.print("Enter Transaction ID: ");
        int txnId = scanner.nextInt();
        scanner.nextLine();
        
        Transaction transaction = findTransactionById(txnId);
        if (transaction == null) {
            System.out.println("✗ Transaction not found!");
            return;
        }
        
        if (transaction.getStatus().equals("RETURNED")) {
            System.out.println("✗ Book already returned!");
            return;
        }
        
        // Update book availability
        inventoryManager.updateBookAvailability(transaction.getBookId(), false);
        
        // Update transaction
        transaction.setReturnDate(new Date());
        transaction.setStatus("RETURNED");
        transaction.calculateFine();
        
        System.out.println("✓ Book returned successfully!");
        if (transaction.getFine() > 0) {
            System.out.println("  ⚠ Fine to pay: Rs. " + transaction.getFine());
        }
    }

    // 3. View All Transactions
    public void viewAllTransactions() {
        System.out.println("\n=== ALL TRANSACTIONS (" + transactions.size() + ") ===");
        if (transactions.isEmpty()) {
            System.out.println("No transactions found.");
            return;
        }
        for (Transaction t : transactions) {
            t.displayInfo();
            System.out.println("----------------------");
        }
    }

    // 4. View User's Transactions
    public void viewUserTransactions() {
        System.out.print("\nEnter User ID: ");
        int userId = scanner.nextInt();
        scanner.nextLine();
        
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getUserId() == userId) {
                t.displayInfo();
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No transactions found for this user.");
    }

    // 5. View Currently Borrowed Books
    public void viewCurrentlyBorrowed() {
        System.out.println("\n=== CURRENTLY BORROWED BOOKS ===");
        boolean found = false;
        for (Transaction t : transactions) {
            if (t.getStatus().equals("BORROWED")) {
                t.displayInfo();
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No books currently borrowed.");
    }

    // 6. View Overdue Books
    public void viewOverdueBooks() {
        System.out.println("\n=== OVERDUE BOOKS ===");
        Date today = new Date();
        boolean found = false;
        
        for (Transaction t : transactions) {
            if (t.getStatus().equals("BORROWED") && today.after(t.getDueDate())) {
                t.displayInfo();
                long daysOverdue = (today.getTime() - t.getDueDate().getTime()) / (1000 * 60 * 60 * 24);
                System.out.println("  ⚠ Days Overdue: " + daysOverdue + " | Fine: Rs. " + (daysOverdue * 10));
                System.out.println("----------------------");
                found = true;
            }
        }
        if (!found) System.out.println("No overdue books.");
    }

    private Transaction findTransactionById(int id) {
        for (Transaction t : transactions) {
            if (t.getTransactionId() == id) return t;
        }
        return null;
    }

    public void menu() {
        System.out.println("\n--- TRANSACTION MENU ---");
        System.out.println("1. Borrow Book");
        System.out.println("2. Return Book");
        System.out.println("3. View All Transactions");
        System.out.println("4. View User Transactions");
        System.out.println("5. View Currently Borrowed");
        System.out.println("6. View Overdue Books");
        System.out.println("0. Back to Main Menu");
        System.out.print("Choice: ");
    }
}
