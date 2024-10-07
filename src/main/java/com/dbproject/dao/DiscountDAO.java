package com.dbproject.dao;

import com.dbproject.domain.Customer;
import com.dbproject.domain.DiscountCode;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;

public class DiscountDAO {

    public static DiscountCode getDiscountCodeByString(String discountString) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            DiscountCode c = Querries.getDiscountCodeByString(session, discountString);
            return c;
        } finally {
            session.close();
        }
    }

    public static DiscountCode getDiscountCodeById(int discountId) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            DiscountCode c = Querries.getDiscountCodeById(session, discountId);
            return c;
        } finally {
            session.close();
        }    
    }

    public static void setUsed(int dcid) {
        var sessionFactory = HibernateUtil.getSessionFactory();
       
        sessionFactory.inTransaction(s ->{
            Querries.setDicountAsUsed(s, dcid);
        });;
    }

}
