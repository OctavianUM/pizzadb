package com.dbproject;

import com.dbproject.dao.CourierDAO;
import com.dbproject.dao.CustomerDAO;
import com.dbproject.dao.MenuDAO;
import com.dbproject.dao.OrderDAO;
import com.dbproject.domain.Adress;
import com.dbproject.domain.Customer;
import com.dbproject.domain.MenuItem;
import com.dbproject.domain.Order;
import com.dbproject.test.CustomerTest;
import com.dbproject.util.*;

import java.sql.Date;
import java.util.*;
import org.hibernate.Session;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;



public class App {
    
    public static void main(String[] args) {
        //initialize database connection when starting the program
        HibernateUtil.getSessionFactory();

        // CustomerTest.test_adding_login_deleting_customer();
        // MenuDOA.retrieveMenu(0).forEach(e -> System.out.println(e.getName()));
        // MenuDOA.getMenuItemIngredients(1).forEach(e -> System.out.println(e.getName()));\
        
        // Order order = new Order(1,  LocalDateTime.now(), 1, OrderStatus.PENDING);
        
        // System.out.println("order created with id = "+ OrderDAO.createOrder(order));
        // OrderDAO.getOrderByID(1);

        CourierDAO.getAvailableCouriers(1002).forEach(e -> System.out.println(e.getCourierID()));


        HibernateUtil.shutdown();
    }
}