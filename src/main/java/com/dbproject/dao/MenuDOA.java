package com.dbproject.dao;

import java.util.ArrayList;

import org.hibernate.Hibernate;

import com.dbproject.domain.Customer;
import com.dbproject.domain.MenuItem;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;

public class MenuDOA {
    public static ArrayList<MenuItem> retrieveMenu(int menuID){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return (ArrayList) Querries.getMenuById(session, menuID);
        } finally {
            session.close();
        }       
    }
}
