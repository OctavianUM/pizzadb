package com.dbproject.cli;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Scanner;

import com.dbproject.dao.MenuDAO;
import com.dbproject.dao.OrderDAO;
import com.dbproject.domain.Order;
import com.dbproject.domain.OrderItem;
import com.dbproject.util.OrderStatus;

public class MenuCLI {
    public static void createOrder(int customerId){
        int orderId = OrderDAO.createOrder(new Order(customerId, LocalDateTime.now(), 0, OrderStatus.PENDING));
        MenuDAO.printMenu(0);
        System.out.println("\nTo add an item to your order:");
        System.out.println("");
        addOrderItem(orderId);
    }

    private static void addOrderItem(int orderId){
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Item ID: ");
        int menuItemId = scanner.nextInt();
        System.out.print("amount: ");
        int amount = scanner.nextInt();

        //TODO create an orderitemDAO to seperate session management 
        OrderItem item = (amount > 0)? new OrderItem(orderId, menuItemId, amount) : null;
    }
}
