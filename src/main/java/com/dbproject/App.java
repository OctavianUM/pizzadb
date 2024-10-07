package com.dbproject;

import com.dbproject.cli.MenuCLI;
import com.dbproject.dao.CourierDAO;
import com.dbproject.dao.CustomerDAO;
import com.dbproject.dao.MenuDAO;
import com.dbproject.dao.OrderDAO;
import com.dbproject.dao.OrderItemDAO;
import com.dbproject.domain.Adress;
import com.dbproject.domain.Customer;
import com.dbproject.domain.MenuItem;
import com.dbproject.domain.Order;
import com.dbproject.domain.OrderItem;
import com.dbproject.util.*;

import java.sql.Date;
import java.util.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class App {
    
    public static void main(String[] args) {
        //initialize database connection when starting the program
        SessionFactory sf = HibernateUtil.getSessionFactory();

        sf.inTransaction(session -> {
            session.createMutationQuery("UPDATE Courier c SET c.status = 'AVAILABLE' where c.status = 'WAITING'")
            .executeUpdate();
        });

        HibernateUtil.shutdown();
    }
}