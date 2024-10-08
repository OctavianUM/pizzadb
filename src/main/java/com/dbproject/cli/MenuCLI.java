package com.dbproject.cli;

import java.util.HashMap;
import java.util.List;
import java.util.Scanner;



import com.dbproject.dao.DiscountDAO;
import com.dbproject.dao.MenuDAO;
import com.dbproject.dao.OrderDAO;
import com.dbproject.dao.OrderItemDAO;
import com.dbproject.domain.DiscountCode;
import com.dbproject.domain.Order;
import com.dbproject.domain.OrderItem;
import com.dbproject.util.OrderStatus;
public class MenuCLI {
    public static int openMenu(int customerId){

        Scanner scanner = new Scanner(System.in);
        HashMap<Integer,Integer> menuItems = new HashMap<>(); //tracks order items

        MenuDAO.printMenu(0);
        System.out.println("\nTo add an item to your order type the 'ID amount' \nto confirm type 'confirm'\nto exit type 'exit'");
        boolean ordering = true;
        while (ordering) {
            int code = parseNext(scanner);
            if(code == 0){
                return Integer.MIN_VALUE;
            }else if(code == -1){
                
                int orderId = placeOrder(customerId, menuItems);
                showOrderInfo(orderId);
                return orderId;
            }else{
                if(menuItems.containsKey(code)){
                    int amount = menuItems.get(code);
                    if(scanner.hasNextInt()){
                        amount += scanner.nextInt();
                    }else{
                        amount += 1;
                    }
                    menuItems.put(code, amount);
                }else{
                    int amount = 1;
                    if(scanner.hasNextInt()){
                        amount = scanner.nextInt();
                    }
                    menuItems.put(code, amount);

                }
            }
        }
        return Integer.MIN_VALUE;
    }
    public static int addDiscount(){
        Scanner scanner = new Scanner(System.in);

        MenuDAO.printMenu(0);
        System.out.println("\nDo you have  a promo code? " );
        String discountString = scanner.next();

        DiscountCode dc = DiscountDAO.getDiscountCodeByString( discountString);
        if (dc != null && !dc.getIsUsed()){
            DiscountDAO.setUsed(dc.getDiscountID());
            scanner.close();
            return dc.getDiscountID();
        }else{
            System.out.println("\ninvalid discount code");
        }
        scanner.close();
        return 0;

    }
    private static int placeOrder(int customerId, HashMap<Integer,Integer> menuItems){
        int code = addDiscount();
        Order order = new Order(customerId, code, OrderStatus.PENDING);
        int orderId = OrderDAO.createOrder(order);

        menuItems.forEach((key, val) -> {
            OrderItemDAO.createOrderItem(new OrderItem(orderId, key, val));
        });
        return orderId;
    }

    private static int parseNext(Scanner scanner){
        if(scanner.hasNextInt()){
            return scanner.nextInt(); 
            
        }
        String in = scanner.next().toLowerCase();
        if(in.equals("confirm")){
            return -1;
        }else if(in.equals("exit")){
            return 0;
        }
        return Integer.MIN_VALUE;
    }

    public static void showOrderInfo(int orderId){
        List<Object[]> orderlist = OrderItemDAO.getOrderItems(orderId);
        Order order = OrderDAO.getOrderByID(orderId);

        System.out.println("\nID: "+ orderId);
        System.out.println("STATUS: "+ order.getStatus());
        System.out.println("PLACED_AT: " + order.getOrderTime());

        System.out.printf("\n%-30s %-10s %-10s%n", "Name", "Amount", "Price");
        System.out.println("-----------------------------------------------------------------");

        // Loop through the array and print each item
        double total = 0;
        for (Object[] item : orderlist) {
            System.out.printf("%-30s %-10s %-10s%n",
                    item[0], // Menu Item ID
                    item[1], // Menu Item Name
                    item[2] // Ingredients
            );
            total += (double)item[2];
        }

        int dc = DiscountDAO.getDiscountCodeById(order.getDiscountId()).getDiscount();
        System.out.println("\nDISCOUNT: "+dc);
        System.out.println("\nTOTAL: " + total*(1.0-dc/10.0));

    }
}
