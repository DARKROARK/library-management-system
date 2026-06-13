public class Student extends User {
    private String studentId;
    private String major;
    private int booksBorrowed;

    public Student(int id, String name, String email, String phone, String studentId, String major) {
        super(id, name, email, phone);
        this.studentId = studentId;
        this.major = major;
        this.booksBorrowed = 0;
    }

    public String getStudentId() { return studentId; }
    public String getMajor() { return major; }
    public int getBooksBorrowed() { return booksBorrowed; }

    public void setMajor(String major) {
        if (!major.trim().isEmpty()) this.major = major;
    }

    public void incrementBooksBorrowed() {
        if (booksBorrowed < 5) booksBorrowed++;
        else System.out.println("Error: Max 5 books limit reached!");
    }

    public void decrementBooksBorrowed() {
        if (booksBorrowed > 0) booksBorrowed--;
        else System.out.println("Error: No books to return!");
    }

    @Override
    public String getRole() { return "Student"; }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("  Student ID: " + studentId + " | Major: " + major + " | Books: " + booksBorrowed);
    }
}