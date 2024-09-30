package com.dbproject.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.dbproject.domain.Adress;
import com.dbproject.domain.Courier;
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

    public static Adress getCustomerAdress(Session session, int id){
        return session.createSelectionQuery(
                "SELECT a "+
                "FROM Customer cu "+
                "JOIN Adress a ON cu.adressId = a.adressId "+
                "WHERE cu.customerId = :id", 
                Adress.class)
                .setParameter("id", id)
                .uniqueResult();
    }

    public static Customer getCustomerById(Session session, int id){
        return session.createSelectionQuery("FROM Customer WHERE customerId = :id", Customer.class)
                    .setParameter("id", id)
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

    public static List<Courier> getAvailableCouriers(Session session, int postal){
        return session.createSelectionQuery(
            "SELECT c " +
            "FROM Courier c " +
            "WHERE c.postal = :postal "+
            "AND c.status IN ('AVAILABLE','WAITING')"
            , Courier.class)
            .setParameter("postal", postal)
            .getResultList();
    }

    public static List<Object[]> getMenu(Session session, int id){
        return session.createSelectionQuery(
            "SELECT mi.menuItemId, mi.name AS menuItemName, " +
            "GROUP_CONCAT(i.name) AS ingredients, " +
            "SUM(i.price * r.amount) AS totalPrice, " +
            "CASE WHEN SUM(CASE WHEN i.dietary = 'Vegetarian' THEN 0 ELSE 1 END) > 0 THEN 'Vegan' ELSE 'Non-vegan' END AS Dietary " +
            "FROM Menu m "+
            "JOIN MenuItem mi ON m.menuItemId = mi.menuItemId "+
            "JOIN Recipe r ON mi.menuItemId = r.menuItemId "+
            "JOIN Ingredient i ON r.ingredientId = i.ingredientId "+
            "WHERE m.menuId = :id GROUP BY mi.menuItemId, mi.name",
            Object[].class)
            .setParameter("id", id)
            .getResultList();
    }

 }


