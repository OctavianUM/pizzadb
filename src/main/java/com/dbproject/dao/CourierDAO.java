package com.dbproject.dao;

import java.util.List;

import com.dbproject.domain.Courier;
import com.dbproject.util.CourierStatus;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;

public class CourierDAO {
    public static List<Courier> getAvailableCouriers(int postal){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getAvailableCouriers(session, postal);
        } finally {
            session.close();
        }
    }

    public static void updateCourierStatus(CourierStatus status){

    }
}

