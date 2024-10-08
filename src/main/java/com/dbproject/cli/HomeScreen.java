package com.dbproject.cli;


    import java.util.Scanner;

    import com.dbproject.util.HibernateUtil;

    public class HomeScreen {

    public static void main(String[] args) {
        HibernateUtil.getSessionFactory();

        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        int customerID = Integer.MIN_VALUE;
        // DeliveryManager deliveryManager = new DeliveryManager();
        //TODO: deliveries and bd-order
        // deliveryManager.delivery(OrderDAO.getOrderByID(1));
        while (true) {
            System.out.println("\n====== MAIN MENU ======");
            System.out.println("(Hidden) 0. admin");
            System.out.println("1. Login");
            System.out.println("2. Menu");
            System.out.println("3. Track order");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 0:
                        AdminMenu.show();
                        break;
                    case 1:
                        customerID = CustomerCLI.customerAccount();
                        break;
                    case 2:
                        if(customerID > 0){
                            MenuCLI.openMenu(customerID);
                            
                        }else{
                            System.out.println("\nYou must login to access the menu");
                        }
                        break;
                    case 3:
                        System.out.print("\ntracking order id: ");
                        int in = scanner.nextInt();
                        System.out.print("\n====== ORDER TRACKING ======");
                        MenuCLI.showOrderInfo(in);
                        
                        break;
                    case 4:
                        System.out.println("Exiting...");
                        scanner.close();
                        HibernateUtil.shutdown();
                        HibernateUtil.shutdown();
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


