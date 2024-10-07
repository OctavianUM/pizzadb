package com.dbproject.dao;

import com.dbproject.util.Querries;
import org.hibernate.Transaction;

import com.dbproject.domain.Delivery;
import com.dbproject.util.HibernateUtil;

import static com.dbproject.domain.Delivery_.orderId;


public class DeliveryDAO {
    public int submitDelivery(Delivery delivery) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            Transaction transaction = session.beginTransaction();

            int id = (int) session.save(delivery);
            transaction.commit();
            return id;
        } finally {

            session.close();
        }
    }

    public static int getPostalCodeByOrderId(int orderID) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getPostalCodeOrderByOrderId(session, orderID);
        } finally {
            session.close();
        }
    }


}