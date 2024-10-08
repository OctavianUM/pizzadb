package com.dbproject.cli;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dbproject.dao.MenuDAO;
import com.dbproject.dao.OrderDAO;
import com.dbproject.dao.OrderItemDAO;
import com.dbproject.util.OrderStatus;

public class AdminMenu {
    public static void show(){
        Scanner scanner = new Scanner(System.in);
        int choice = 0;
        while (true) {
            System.out.println("\n====== ADMIN MENU ======");
            System.out.println("1. Earnings report");
            System.out.println("2. Order listing");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            
            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.println("\n====== EARNINGS REPORT ======");
                        MenuDAO.getTotalMenuItemsForMonthYear(LocalDate.now().getMonthValue(),LocalDate.now().getYear());
                        break;
                        
                    case 2:
                        System.out.println("\n====== ORDER LISTING ======");
                        showOrderInfo(OrderStatus.PENDING);
                        
                        break;
                    case 3:
                        return;
                    default:
                        System.out.println("Invalid choice.");
                }
            } else {
                System.out.println("Invalid input. Please enter a number.");
                scanner.next(); // Clear invalid input
            }
            scanner.close();
        }
    }

    public static void showOrderInfo(OrderStatus status){
        
        Scanner scanner = new Scanner(System.in);

        while (true) {
            ArrayList<Integer> orderIdList = (ArrayList<Integer>) OrderDAO.getOrderByStatus(OrderStatus.PENDING);
            for (int orderId : orderIdList) {
                List<Object[]> orderlist = OrderItemDAO.getOrderItems(orderId);

                System.out.println("\nID: "+ orderId);

                System.out.printf("\n%-30s %-10s%n", "Name", "Amount");
                System.out.println("---------------------------------------------");

                // Loop through the array and print each item
                for (Object[] item : orderlist) {
                    System.out.printf("%-30s %-10s%n",
                            item[0], // 
                            item[1]
                    );
                }

            }
            System.out.println("\nAccept order (id?): ");

            //TODO add to delivery
            scanner.nextInt();
            
            scanner.close();
        }
        
        
    }
}
