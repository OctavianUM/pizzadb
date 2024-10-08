package com.dbproject.cli;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.dbproject.dao.OrderDAO;
import com.dbproject.dao.OrderItemDAO;
import com.dbproject.util.DeliveryManager;
import com.dbproject.util.OrderStatus;

public class OrderCLI {
    public static void show(Scanner scanner){


        while (true) {
            System.out.println("\nACCEPT AN ORDER: (to exit type 0)");

            showOrderInfo(OrderStatus.PENDING);
            
            if (scanner.hasNextInt()) {
                int orderId = scanner.nextInt();
                switch (orderId) {
                    case 0: //exit
                        return;
                    default:
                        new DeliveryManager().delivery(OrderDAO.getOrderByID(orderId));
                        OrderDAO.updateOrderStatus(orderId, OrderStatus.OUT_FOR_DELIVERY);
                        break;
                }
            }
            break;
        }
    }

    public static void showOrderInfo(OrderStatus status){
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
    }
}
