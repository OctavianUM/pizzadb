package com.dbproject.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.dbproject.domain.Order;
import com.dbproject.domain.OrderItem;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;

import java.util.List;

public class OrderItemDAO {
    public static int createOrderItem(OrderItem item){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            int id = (int) session.save(item);
            transaction.commit();
            return id;
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
}
