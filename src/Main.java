import java.util.Scanner;

public class Main {
    private static UserManager userManager;
    private static InventoryManager inventoryManager;
    private static Scanner scanner;

    public static void main(String[] args) {
        userManager = new UserManager();
        inventoryManager = new InventoryManager();
        scanner = new Scanner(System.in);
        int choice;

        System.out.println("\n=== LIBRARY MANAGEMENT SYSTEM ===");

        do {
            System.out.println("\n1. User Module");
            System.out.println("2. Inventory Module");
            System.out.println("0. Exit");
            System.out.print("Choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) userMenu();
            else if (choice == 2) inventoryMenu();
            else if (choice == 0) System.out.println("Goodbye!");
            else System.out.println("Invalid!");
        } while (choice != 0);
    }

    private static void userMenu() {
        int choice;
        do {
            userManager.menu();
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) userManager.addUser();
            else if (choice == 2) userManager.viewAllUsers();
            else if (choice == 3) userManager.searchById();
            else if (choice == 4) userManager.searchByName();
            else if (choice == 5) userManager.updateUser();
            else if (choice == 6) userManager.deleteUser();
        } while (choice != 0);
    }

    private static void inventoryMenu() {
        int choice;
        do {
            inventoryManager.menu();
            choice = scanner.nextInt();
            scanner.nextLine();
            if (choice == 1) inventoryManager.addBook();
            else if (choice == 2) inventoryManager.viewAllBooks();
            else if (choice == 3) inventoryManager.searchById();
            else if (choice == 4) inventoryManager.searchByTitle();
            else if (choice == 5) inventoryManager.searchByAuthor();
            else if (choice == 6) inventoryManager.updateBook();
            else if (choice == 7) inventoryManager.deleteBook();
            else if (choice == 8) inventoryManager.checkAvailability();
        } while (choice != 0);
    }
}
