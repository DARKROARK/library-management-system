import java.util.Date;
import java.text.SimpleDateFormat;

public class Transaction {
    private int transactionId;
    private int userId;
    private String userName;
    private int bookId;
    private String bookTitle;
    private Date borrowDate;
    private Date dueDate;
    private Date returnDate;
    private String status; // "BORROWED", "RETURNED", "OVERDUE"
    private double fine;

    // Constructor for new borrow
    public Transaction(int transactionId, int userId, String userName, 
                      int bookId, String bookTitle, Date borrowDate, Date dueDate) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.userName = userName;
        this.bookId = bookId;
        this.bookTitle = bookTitle;
        this.borrowDate = borrowDate;
        this.dueDate = dueDate;
        this.status = "BORROWED";
        this.fine = 0.0;
        this.returnDate = null;
    }

    // Getters
    public int getTransactionId() { return transactionId; }
    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public int getBookId() { return bookId; }
    public String getBookTitle() { return bookTitle; }
    public Date getBorrowDate() { return borrowDate; }
    public Date getDueDate() { return dueDate; }
    public Date getReturnDate() { return returnDate; }
    public String getStatus() { return status; }
    public double getFine() { return fine; }

    // Setters
    public void setReturnDate(Date returnDate) { this.returnDate = returnDate; }
    public void setStatus(String status) { this.status = status; }
    public void setFine(double fine) { this.fine = fine; }

    // Calculate fine (Rs. 10 per day overdue)
    public void calculateFine() {
        if (status.equals("RETURNED") && returnDate != null) {
            if (returnDate.after(dueDate)) {
                long diff = returnDate.getTime() - dueDate.getTime();
                long daysOverdue = diff / (1000 * 60 * 60 * 24);
                fine = daysOverdue * 10;
            }
        }
    }

    // Get formatted date string
    public String getFormattedBorrowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(borrowDate);
    }

    public String getFormattedDueDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        return sdf.format(dueDate);
    }

    public void displayInfo() {
        System.out.println("Txn ID: " + transactionId);
        System.out.println("  User: " + userName + " (ID: " + userId + ")");
        System.out.println("  Book: " + bookTitle + " (ID: " + bookId + ")");
        System.out.println("  Borrowed: " + getFormattedBorrowDate());
        System.out.println("  Due Date: " + getFormattedDueDate());
        System.out.println("  Status: " + status);
        if (fine > 0) System.out.println("  Fine: Rs. " + fine);
    }
}
