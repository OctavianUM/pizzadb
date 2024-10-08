package com.dbproject.dao;

import java.time.LocalDateTime;
import java.util.List;

import com.dbproject.domain.Courier;
import com.dbproject.util.CourierStatus;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;
import org.hibernate.Transaction;

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

    public static List<Courier> getWaitingCouriers(int postal){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getWaitingCouriers(session, postal);
        } finally {
            session.close();
        }
    }

    public static void updateCourierStatus(Courier courier, CourierStatus status){
        String temp= courier.getStatus();
        courier.setStatus(status.getStatus());
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        if (temp.equals("AVAILABLE")){
            try {
                Transaction transaction = session.beginTransaction();
                courier.setTimeLastDelivery(LocalDateTime.now());
                session.merge(courier);
                session.flush();
                transaction.commit();
            }   finally {
                session.close();

            }
        }
        else if (temp.equals("WAITING")) {
            LocalDateTime now = LocalDateTime.now();
            LocalDateTime timeLastDelivery = courier.getTimeLastDelivery();

            if (now.isAfter(timeLastDelivery.plusMinutes(5)) && now.isBefore(timeLastDelivery.plusMinutes(30))) {
                try {
                    Transaction transaction = session.beginTransaction();
                    courier.setStatus("DELIVERING");
                    session.merge(courier);
                    transaction.commit();
                } finally {
                    session.close();
                }
            }
        }
        else {
            try {
                Transaction transaction = session.beginTransaction();
                courier.setStatus("AVAILABLE");
                session.merge(courier);
                transaction.commit();
            } finally {
                session.close();
            }
        }

    }

}

