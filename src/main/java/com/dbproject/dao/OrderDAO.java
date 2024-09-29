package com.dbproject.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Transaction;

import com.dbproject.domain.Order;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.PasswordEncrypt;
import com.dbproject.util.Querries;

public class OrderDAO {
    public static int createOrder(Order order){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            int id = (int) session.save(order);
            transaction.commit();
            return id;
        } finally {
            
            session.close();
        }
    }

    // public static boolean updateOrderStatus(){

    // }

    // public static boolean removeOrder(){

    // }

    // public static List<Order> getOrderByStatus(){

    // }

    public static Order getOrderByID(int orderId){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getOrderByID(session, orderId);
        } finally {
            session.close();
        }
    }
}
