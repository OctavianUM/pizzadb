package com.dbproject.util;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.dbproject.domain.Adress;
import com.dbproject.domain.Courier;
import com.dbproject.domain.Customer;
import com.dbproject.domain.DiscountCode;
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

    public static List<Integer> getOrderByStatus(Session session, OrderStatus status){
        return session.createSelectionQuery(
            "SELECT o.orderId " +
            "FROM Order o " +
            "WHERE o.status = :status"
            , Integer.class)
            .setParameter("status", status.toString())
            .getResultList();
    }
    public static Short getPostalCodeOrderByOrderId(Session session, int orderId) {
        return session.createSelectionQuery("SELECT a.postal " +
                "FROM Order o " +
                "JOIN Customer c ON o.customerId = c.customerId " +
                "JOIN Adress  a ON c.adressId = a.adressId " +
                "WHERE o.orderId = :orderID", Short.class)
                .setParameter("orderID", orderId)
                .uniqueResult();
    }
    public static void updateCourierTimeLastDelivery(Session session, int courierID) {
        session.createMutationQuery("UPDATE Courier " +
                        "SET timeLastDelivery = CURRENT_TIMESTAMP " +
                        "WHERE courierID = :courierID")
                .setParameter("courierID", courierID)
                .executeUpdate();
        session.flush();
    }


    public static List<Courier> getAvailableCouriers(Session session, int postal){
        return session.createSelectionQuery(
            "SELECT c " +
            "FROM Courier c " +
            "WHERE c.postal = :postal "+
            "AND c.status IN ('AVAILABLE')"
            , Courier.class)
            .setParameter("postal", postal)
            .getResultList();
    }
    public static List<Courier> getWaitingCouriers(Session session, int postal){
        return session.createSelectionQuery(
                        "SELECT c " +
                                "FROM Courier c " +
                                "WHERE c.postal = :postal "+
                                "AND c.status IN ('WAITING')"
                        , Courier.class)
                .setParameter("postal", postal)
                .getResultList();
    }

    public static List<Object[]> getMenu(Session session, int id){
        
        return session.createSelectionQuery(
            "SELECT mi.menuItemId, mi.name AS menuItemName, " +
            "GROUP_CONCAT(i.name) AS ingredients, " +
            "SUM(i.price * r.amount * 1.40 * 1.09) AS totalPrice, " + //also adds vat + profit margin
            "CASE WHEN SUM(CASE WHEN i.dietary = 'Vegetarian' THEN 0 ELSE 1 END) > 0 THEN 'Non-vegan' ELSE 'Vegan' END AS Dietary " +
            "FROM Menu m "+
            "JOIN MenuItem mi ON m.menuItemId = mi.menuItemId "+
            "JOIN Recipe r ON mi.menuItemId = r.menuItemId "+
            "JOIN Ingredient i ON r.ingredientId = i.ingredientId "+
            "WHERE m.menuId = :id GROUP BY mi.menuItemId, mi.name",
            Object[].class)
            .setParameter("id", id)
            .getResultList();
    }

    public static List<Object[]> getOrderItems(Session session, int orderId){
        return
        session.createSelectionQuery(
            
        "SELECT mi.name, oi.quantity, (SUM(i.price * r.amount * 1.40 * 1.09) * oi.quantity) "+
        "FROM OrderItem oi "+
        "JOIN MenuItem mi ON oi.menuItemId = mi.menuItemId "+
        "JOIN Recipe r ON mi.menuItemId = r.menuItemId "+
        "JOIN Ingredient i ON r.ingredientId = i.ingredientId " +
        "WHERE oi.orderId = :orderId " +
        "GROUP BY mi.menuItemId, oi.quantity"
            , Object[].class)
            .setParameter("orderId", orderId)
            .getResultList();
    }


    //TODO: change to get all deliveries and orders + discountcode
    public static List<Object[]> getTotalMenuItemsForMonthYear(Session session, int month, int year){
        String query = 
        """
            SELECT 
                mi.name,
                SUM(oi.quantity) AS total_quantity_ordered,
                recipe_cost.total_item_price AS menuitem_price,
                (SUM(oi.quantity) * recipe_cost.total_item_price) AS total_price
            FROM 
                OrderItem oi
            JOIN 
                MenuItem mi ON oi.menuItemId = mi.menuItemId
            JOIN 
                Order o ON oi.orderId = o.orderId
            JOIN 
                (
                    SELECT 
                        r.menuItemId AS menuItemId,
                        SUM(i.price * r.amount  * 1.40 * 1.09) AS total_item_price
                    FROM 
                        Recipe r
                    JOIN 
                        Ingredient i ON r.ingredientId = i.ingredientId
                    GROUP BY 
                        r.menuItemId
                ) recipe_cost ON mi.menuItemId = recipe_cost.menuItemId
            WHERE 
                MONTH(o.orderTime) = :month
                AND YEAR(o.orderTime) = :year
            GROUP BY 
                mi.menuItemId
            ORDER BY 
                total_quantity_ordered DESC
            """;
            return session.createSelectionQuery(query, Object[].class)
            .setParameter("month", month)
            .setParameter("year", year)
            .getResultList();
    }

    public static List<Object[]> getTotalDeliveriesMonth(Session session, int month, int year){
        String query = 
        """
            SELECT oi.menuItemId
            FROM Delivery d 
            JOIN (
                    SELECT 
                        r.menuItemId AS menuItemId,
                        SUM(i.price * r.amount  * 1.40 * 1.09) AS total_item_price
                    FROM 
                        Recipe r
                    JOIN 
                        Ingredient i ON r.ingredientId = i.ingredientId
                    GROUP BY 
                        r.menuItemId
                ) recipe_cost ON mi.menuItemId = recipe_cost.menuItemId
            JOIN OrderItem oi ON d.orderId = oi.orderId 
            JOIN Order o ON d.orderId = o.orderId 
            WHERE MONTH(o.orderTime) = :month AND YEAR(o.orderTime) = :year
            """;
            return session.createSelectionQuery(query, Object[].class)
            .setParameter("month", month)
            .setParameter("year", year)
            .getResultList();
    }

    public static DiscountCode getDiscountCodeByString(Session session, String discountString) {
        return session.createSelectionQuery(
                "SELECT dc "+
                "FROM DiscountCode dc "+
                "WHERE dc.discountString  = :str", 
                DiscountCode.class)
                .setParameter("str", discountString)
                .uniqueResult();
    }

    public static DiscountCode getDiscountCodeById(Session session, int discountId) {
        return session.createSelectionQuery(
                "SELECT dc "+
                "FROM DiscountCode dc "+
                "WHERE dc.discountID  = :id", 
                DiscountCode.class)
                .setParameter("id", discountId)
                .uniqueResult();    
    }

    public static void setDicountAsUsed(Session session, int dcid) {
        session.createMutationQuery("UPDATE DiscountCode dc SET dc.isUsed = true WHERE dc.discountID = :dcid").setParameter("dcid", dcid).executeUpdate();
    }

 }


