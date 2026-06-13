import java.util.ArrayList;
import java.util.Scanner;

public class UserManager {
    private ArrayList<User> users;
    private Scanner scanner;
    private int nextId;

    public UserManager() {
        users = new ArrayList<>();
        scanner = new Scanner(System.in);
        nextId = 1;
        // Sample data
        users.add(new Student(nextId++, "Alice Khan", "alice@uni.edu", "03123456789", "STU001", "CS"));
        users.add(new Student(nextId++, "Bob Ahmed", "bob@uni.edu", "03234567890", "STU002", "Math"));
        users.add(new Staff(nextId++, "Dr. Sarah", "sarah@lib.edu", "03345678901", "EMP001", "Library", "Librarian"));
    }

    public void addUser() {
        System.out.println("\n=== ADD USER ===");
        System.out.println("1. Student");
        System.out.println("2. Staff");
        System.out.print("Choice: ");
        int type = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Phone: ");
        String phone = scanner.nextLine();

        if (type == 1) {
            System.out.print("Student ID: ");
            String studentId = scanner.nextLine();
            System.out.print("Major: ");
            String major = scanner.nextLine();
            users.add(new Student(nextId++, name, email, phone, studentId, major));
            System.out.println("✓ Student added! ID: " + (nextId-1));
        } else if (type == 2) {
            System.out.print("Employee ID: ");
            String empId = scanner.nextLine();
            System.out.print("Department: ");
            String dept = scanner.nextLine();
            System.out.print("Position: ");
            String pos = scanner.nextLine();
            users.add(new Staff(nextId++, name, email, phone, empId, dept, pos));
            System.out.println("✓ Staff added! ID: " + (nextId-1));
        }
    }

    public void viewAllUsers() {
        System.out.println("\n=== ALL USERS (" + users.size() + " total) ===");
        for (User u : users) {
            u.displayInfo();
            System.out.println("----------------------");
        }
    }

    public void searchById() {
        System.out.print("\nEnter ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (User u : users) {
            if (u.getId() == id) {
                u.displayInfo();
                return;
            }
        }
        System.out.println("User not found!");
    }

    public void searchByName() {
        System.out.print("\nEnter name: ");
        String name = scanner.nextLine();
        boolean found = false;
        for (User u : users) {
            if (u.getName().toLowerCase().contains(name.toLowerCase())) {
                u.displayInfo();
                found = true;
            }
        }
        if (!found) System.out.println("No users found!");
    }

    public void updateUser() {
        System.out.print("\nEnter ID to update: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (User u : users) {
            if (u.getId() == id) {
                System.out.print("New name (" + u.getName() + "): ");
                String name = scanner.nextLine();
                if (!name.isEmpty()) u.setName(name);
                System.out.print("New email (" + u.getEmail() + "): ");
                String email = scanner.nextLine();
                if (!email.isEmpty()) u.setEmail(email);
                System.out.print("New phone (" + u.getPhone() + "): ");
                String phone = scanner.nextLine();
                if (!phone.isEmpty()) u.setPhone(phone);
                System.out.println("✓ Updated!");
                return;
            }
        }
        System.out.println("User not found!");
    }

    public void deleteUser() {
        System.out.print("\nEnter ID to delete: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId() == id) {
                System.out.print("Delete " + users.get(i).getName() + "? (y/n): ");
                if (scanner.nextLine().equalsIgnoreCase("y")) {
                    users.remove(i);
                    System.out.println("✓ Deleted!");
                }
                return;
            }
        }
        System.out.println("User not found!");
    }

    public void menu() {
        System.out.println("\n=== USER MODULE ===");
        System.out.println("1. Add User");
        System.out.println("2. View All");
        System.out.println("3. Search by ID");
        System.out.println("4. Search by Name");
        System.out.println("5. Update");
        System.out.println("6. Delete");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }
}