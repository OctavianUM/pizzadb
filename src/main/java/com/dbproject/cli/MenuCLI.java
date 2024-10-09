package com.dbproject.cli;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

import com.dbproject.dao.CustomerDAO;
import com.dbproject.dao.DiscountDAO;
import com.dbproject.dao.MenuDAO;
import com.dbproject.dao.MenuItemDAO;
import com.dbproject.dao.OrderDAO;
import com.dbproject.dao.OrderItemDAO;
import com.dbproject.domain.DiscountCode;
import com.dbproject.domain.Order;
import com.dbproject.domain.OrderItem;
import com.dbproject.util.OrderStatus;
import com.dbproject.util.Querries;
public class MenuCLI {

    private static boolean isBirthday = false;
    private static boolean validBdayOrder = false;
    private static int customerID;

    public static int openMenu(Scanner scanner, int customerId){
        customerID = customerId;
        Date bday_date = CustomerDAO.getCustomerById(customerId).getBirthDate();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateString = dateFormat.format(bday_date);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate bday = LocalDate.parse(dateString, formatter);
        isBirthday = bday.getMonth().equals(LocalDate.now().getMonth()) && bday.getDayOfMonth() == (LocalDate.now().getDayOfMonth());

        if(isBirthday){
            System.out.println("Happy BirthDay! You can get a order for a pizza and drink.");
        }

        HashMap<Integer,Integer> menuItems = new HashMap<>(); //tracks order items

        MenuDAO.printMenu(0);
        System.out.println("\nTo add an item to your order type the 'ID amount' \nto confirm type 'confirm'\nto exit type 'exit'");
        boolean ordering = true;
        while (ordering) {
            int code = parseNext(scanner);
            if(code == 0){
                return Integer.MIN_VALUE;
            }else if(code == -1){
                validBdayOrder = checkBdayOrder(menuItems);
                int orderId = placeOrder(customerId, menuItems, scanner);
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

    private static boolean checkBdayOrder(HashMap<Integer, Integer> menuItems) {

        if(menuItems.size() == 2 && sum(menuItems.values()) == 2){
            Object[] itemID = menuItems.keySet().toArray();
            return (MenuItemDAO.getMenuItemById((int)itemID[0]).getType().equals("pizza") &&
            MenuItemDAO.getMenuItemById((int)itemID[1]).getType().equals("drink")) ||
            (MenuItemDAO.getMenuItemById((int)itemID[1]).getType().equals("pizza") &&
            MenuItemDAO.getMenuItemById((int)itemID[0]).getType().equals("drink")) ;
        }
        return false;

    }

    private static int sum(Collection<Integer> values) {
        int total = 0;
        for (Integer integer : values) {
            total += integer;
        }
        return total;
    }

    public static int addDiscount(Scanner scanner){
        int num_pizzas = CustomerDAO.getPizzasOrderedByCustomerById(customerID);
        if (validBdayOrder){
            System.out.println("Your was eligible for the b-day discount. the discount is applied automatically");
            return 2;
        } else if(num_pizzas>0 && num_pizzas%10 == 0){
            System.out.println("You ordered 10 pizzas. A 10% discount is applied automatically");
            return 1;
        } else {

            MenuDAO.printMenu(0);
            System.out.println("\nDo you have  a promo code (put - if not)? " );
            String discountString = scanner.next();

            DiscountCode dc = DiscountDAO.getDiscountCodeByString( discountString);
            if(dc != null && !dc.getIsUsed()){
                DiscountDAO.setUsed(dc.getDiscountID());
                return dc.getDiscountID();
            }else if(discountString.equals("-")){
                return 3;
            }else{
                System.out.println("\ninvalid discount code");
                return 3;
            }

        }

    }
    private static int placeOrder(int customerId, HashMap<Integer,Integer> menuItems, Scanner scanner){
        int code = addDiscount(scanner);
        int orderId = OrderDAO.createOrder(new Order(customerId, code, OrderStatus.PENDING));

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
        System.out.println("\nDISCOUNT: "+dc+"%");
        System.out.println("\nTOTAL: " + total*(1.0-dc/100.0));

    }
}
