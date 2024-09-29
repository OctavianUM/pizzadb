package com.dbproject.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.dbproject.domain.Adress;
import com.dbproject.domain.Customer;
import com.dbproject.domain.Ingredient;
import com.dbproject.domain.MenuItem;
import com.dbproject.domain.Order;

public class Querries {
    public static Adress getAdressByStreet(Session session, String street){
        return session.createNamedQuery("select_adress_by_street", Adress.class)
                .setParameter("street", "Paul-Henri Spaaklaan")
                .getSingleResult();
    }

    public static List<MenuItem> getMenuById(Session session, int id){
        return session.createQuery(
                "SELECT mi "+
                "FROM Menu m "+
                "LEFT JOIN MenuItem mi ON m.menuItemId = mi.menuItemId "+
                "WHERE m.menuId = :id"
                , MenuItem.class)
            .setParameter("id", id)
            .getResultList();
    }

    public static Customer getCustomerByEmail(Session session, String email){
        return session.createSelectionQuery("FROM Customer WHERE email = :email", Customer.class)
                    .setParameter("email", email)
                    .uniqueResult();
    }


    public static List<Ingredient> getMenuItemIngredients(Session session, int menuItemId){
        return session.createSelectionQuery(
            "SELECT i " +
            "FROM Recipe r " +
            "JOIN Ingredient i ON r.ingredientId = i.ingredientId " +
            "WHERE r.menuItemId = :menuItemId"
            , Ingredient.class)
            .setParameter("menuItemId", menuItemId)
            .getResultList();
    }

    
    public static List<Ingredient> getMenuItemIngredients(Session session, String menuItemName){
        return session.createSelectionQuery(
            "SELECT i " +
            "FROM Recipe r " +
            "JOIN Ingredient i ON r.ingredientId = i.ingredientId " +
            "JOIN MenuItem m ON r.menuItemId = m.menuItemId "+
            "WHERE m.name = :menuItemName"
            , Ingredient.class)
            .setParameter("menuItemName", menuItemName)
            .getResultList();
    }

    public static Order getOrderByID(Session session, int orderId){
        return session.createSelectionQuery(
            "SELECT o " +
            "FROM Order o " +
            "WHERE o.orderId = :orderId"
            , Order.class)
            .setParameter("orderId", orderId)
            .uniqueResult();
    }
}
