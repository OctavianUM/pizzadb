package com.dbproject.dao;

import org.hibernate.Transaction;

import com.dbproject.domain.Delivery;
import com.dbproject.util.HibernateUtil;


public class DeliveryDAO {
    public int submitDelivery(Delivery delivery){
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
}
