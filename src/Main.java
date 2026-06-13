public class Main {
    public static void main(String[] args) {
        UserManager um = new UserManager();
        java.util.Scanner sc = new java.util.Scanner(System.in);
        int choice;
        
        System.out.println("=== LIBRARY SYSTEM - USER MODULE ===");
        
        do {
            um.menu();
            choice = sc.nextInt();
            sc.nextLine();
            
            switch(choice) {
                case 1: um.addUser(); break;
                case 2: um.viewAllUsers(); break;
                case 3: um.searchById(); break;
                case 4: um.searchByName(); break;
                case 5: um.updateUser(); break;
                case 6: um.deleteUser(); break;
                case 0: System.out.println("Goodbye!"); break;
                default: System.out.println("Invalid!");
            }
        } while(choice != 0);
        sc.close();
    }
}