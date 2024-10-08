package com.dbproject.cli;

import java.util.Scanner;

public class AdminMenu {
    public static void show(Scanner scanner){
        while (true) {
            System.out.println("\n====== ADMIN MENU ======");
            System.out.println("1. Earnings report");
            System.out.println("2. Order listing");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            String in = scanner.next();

            try{
                int choice = Integer.parseInt(in);
                switch (choice) {
                    case 1:
                        ReportCLI.show(scanner);
                        break;
                        
                    case 2:
                        System.out.println("\n====== ORDER LISTING ======");    
                        OrderCLI.show(scanner);
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            }finally{}
        }
                
        
    }
}
