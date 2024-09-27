package com.dbproject;

import com.dbproject.domain.Adress;
import com.dbproject.domain.MenuItem;
import com.dbproject.util.*;
import java.util.*;
import org.hibernate.Session;



public class App {
    
    public static void main(String[] args) {
        var sessionFactory = HibernateUtil.getSessionFactory();

        Session session = sessionFactory.openSession();

        // query data using HQL
        System.out.println(
            Querries.getAdressByStreet(session, "Paul-Henri Spaaklaan").getStreet()

        );

        Querries.getMenuById(session, 0)
            .forEach(e -> {
                System.out.println(e.getName());
            });
        

        HibernateUtil.shutdown();
    }
}