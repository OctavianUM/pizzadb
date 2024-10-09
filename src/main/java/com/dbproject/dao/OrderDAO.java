package com.dbproject.dao;

import java.util.List;

import org.hibernate.Transaction;

import com.dbproject.domain.Order;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.OrderStatus;
import com.dbproject.util.Querries;

public class OrderDAO {
    public static int createOrder(Order order){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            session.persist(order);
            session.flush();
            transaction.commit();
            return order.getOrderId();
        } finally {
            
            session.close();
        }
    }

    public static List<Integer> getOrderByStatus(OrderStatus status){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getOrderByStatus(session, status);
        } finally {
            session.close();
        }

    }

    public static Order getOrderByID(int orderId){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getOrderByID(session, orderId);
        } finally {
            session.close();
        }

    }

    public static List<Object[]> getOrderItems(int orderId){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getOrderItems(session, orderId);
        } finally {
            session.close();
        }
    }

    public static void updateOrderStatus(int orderId, OrderStatus status) {
        HibernateUtil.getSessionFactory().inTransaction(session ->{
            Querries.changeStatus(session, orderId, status.toString());
        });
    }
}
