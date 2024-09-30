package com.dbproject.cli;


    import java.util.Scanner;

import com.dbproject.dao.MenuDAO;

    public class HomeScreen {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int customerID = 0;

        while (true) {
            System.out.println("\n====== MAIN MENU ======");
            System.out.println("1. Login");
            System.out.println("2. Menu");
            System.out.println("3. Exit");
            System.out.println("(Hidden) 4. admin");
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        CustomerCLI.customerAccount();
                        break;
                    case 2:
                        MenuCLI.createOrder(customerID);

                        break;
                    case 3:
                        System.out.println("Exiting...");
                        scanner.close();
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice. Please select 1, 2, or 3.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
        }
    }

    public static void adminMenu() {
        System.out.println("You selected to See Admin Menu.");
        // Add admin menu logic here
    }
}


