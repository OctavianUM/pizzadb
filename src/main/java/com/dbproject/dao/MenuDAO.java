package com.dbproject.dao;

import java.util.ArrayList;

import org.hibernate.Hibernate;

import com.dbproject.domain.Customer;
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
            return (ArrayList) Querries.getMenuById(session, menuID);
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
            return (ArrayList) Querries.getMenuItemIngredients(session, menuItemName);
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
            return (ArrayList) Querries.getMenuItemIngredients(session, menuItemId);
        } finally {
            session.close();
        } 
    }
}
