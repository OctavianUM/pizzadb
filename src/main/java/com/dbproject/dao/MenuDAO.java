package com.dbproject.dao;

import java.util.ArrayList;
import java.util.List;

import com.dbproject.domain.Ingredient;
import com.dbproject.domain.MenuItem;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;

public class MenuDAO {
    /**
     * Retrieves a list of menu items for a specified menu by its ID.
     *
     * @param menuID The ID of the menu whose items are to be retrieved.
     * @return An ArrayList of MenuItem objects for the specified menu.
     */
    public static ArrayList<MenuItem> retrieveMenu(int menuID){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return (ArrayList<MenuItem>) Querries.getMenuById(session, menuID);
        } finally {
            session.close();
        }       
    }

    /**
     * Retrieves a list of ingredients for a given menu item by its name.
     *
     * @param menuItemName The name of the menu item whose ingredients are to be retrieved.
     * @return An ArrayList of Ingredient objects for the specified menu item.
     */
    public static ArrayList<Ingredient> getMenuItemIngredients(String menuItemName){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return (ArrayList<Ingredient>) Querries.getMenuItemIngredients(session, menuItemName);
        } finally {
            session.close();
        } 
    }

    /**
     * Retrieves a list of ingredients for a given menu item by its ID.
     *
     * @param menuItemId The ID of the menu item whose ingredients are to be retrieved.
     * @return An ArrayList of Ingredient objects for the specified menu item.
     */
    public static ArrayList<Ingredient> getMenuItemIngredients(int menuItemId){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return (ArrayList<Ingredient>) Querries.getMenuItemIngredients(session, menuItemId);
        } finally {
            session.close();
        } 
    }

    public static void printMenu(int id){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            List<Object[]> menu = Querries.getMenu(session, id);
            
            System.out.printf("%-10s %-30s %-90s %-11s %-10s%n", "ID", "Name", "Ingredients", "Total Price", "Dietary");
            System.out.println("--------------------------------------------------------------------------------------------------------------------------------------------------------------------");

            // Loop through the array and print each item
            for (Object[] item : menu) {
                System.out.printf("%-10s %-30s %-90s $%-11s %-10s%n",
                        item[0], // Menu Item ID
                        item[1], // Menu Item Name
                        item[2],
                        item[3], // Total Price
                        item[4]); // Dietary
            }

        } finally {
            session.close();
        } 
    }

    public static void getTotalMenuItemsByGender(char gender){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            List<Object[]> objectList = Querries.getTotalMenuItemsByGender(session, gender);
            
            System.out.printf("%-10s %-30s %-10s %-10s%n",  "Id", "Name", "Amount", "Total Price");
            System.out.println("-------------------------------------------------------------------");

            // Loop through the array and print each item
            double total_item_price = 0;
            for (Object[] item : objectList) {
                System.out.printf("%-10s %-30s %-10s $%-10s%n",
                        item[0], 
                        item[1], 
                        item[2],
                        item[3]
                    );
                total_item_price += (double) item[3];
            }

            
            double vat = total_item_price - total_item_price / 1.09;
            double profit = total_item_price -(total_item_price / 1.09)/ 1.4;

            System.out.println("\nTotal profit: " + profit );
            System.out.println("Total VAT: " + vat );
            System.out.println("Total ingredient cost: " + (total_item_price - vat - profit));

            System.out.println("\nTotal income: " + total_item_price);

        } finally {
            session.close();
        } 
    }


    public static void getTotalMenuItemsByAge(int age){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            List<Object[]> objectList = Querries.getTotalMenuItemsByAge(session, age);
            
            System.out.printf("%-10s %-30s %-10s %-10s%n",  "Id", "Name", "Amount", "Total Price");
            System.out.println("-------------------------------------------------------------------");

            // Loop through the array and print each item
            double total_item_price = 0;
            for (Object[] item : objectList) {
                System.out.printf("%-10s %-30s %-10s $%-10s%n",
                        item[0], 
                        item[1], 
                        item[2],
                        item[3]
                    );
                total_item_price += (double) item[3];
            }

            
            double vat = total_item_price - total_item_price / 1.09;
            double profit = total_item_price -(total_item_price / 1.09)/ 1.4;

            System.out.println("\nTotal profit: " + profit );
            System.out.println("Total VAT: " + vat );
            System.out.println("Total ingredient cost: " + (total_item_price - vat - profit));

            System.out.println("\nTotal income: " + total_item_price);

        } finally {
            session.close();
        } 
    }


    public static void getTotalMenuItemsByPostal(int postal){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            List<Object[]> objectList = Querries.getTotalMenuItemsByPostal(session, postal);
            
            System.out.printf("%-10s %-30s %-10s %-10s%n",  "Id", "Name", "Amount", "Total Price");
            System.out.println("-------------------------------------------------------------------");

            // Loop through the array and print each item
            double total_item_price = 0;
            for (Object[] item : objectList) {
                System.out.printf("%-10s %-30s %-10s $%-10s%n",
                        item[0], 
                        item[1], 
                        item[2],
                        item[3]
                    );
                total_item_price += (double) item[3];
            }

            
            double vat = total_item_price - total_item_price / 1.09;
            double profit = total_item_price -(total_item_price / 1.09)/ 1.4;

            System.out.println("\nTotal profit: " + profit );
            System.out.println("Total VAT: " + vat );
            System.out.println("Total ingredient cost: " + (total_item_price - vat - profit));

            System.out.println("\nTotal income: " + total_item_price);

        } finally {
            session.close();
        } 
    }

}
