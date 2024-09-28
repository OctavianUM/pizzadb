package com.dbproject;

import com.dbproject.dao.CustomerDAO;
import com.dbproject.dao.MenuDOA;
import com.dbproject.domain.Adress;
import com.dbproject.domain.Customer;
import com.dbproject.domain.MenuItem;
import com.dbproject.test.CustomerTest;
import com.dbproject.util.*;

import java.sql.Date;
import java.util.*;
import org.hibernate.Session;



public class App {
    
    public static void main(String[] args) {
        //initialize database connection when starting the program
        HibernateUtil.getSessionFactory();

        // CustomerTest.test_adding_login_deleting_customer();
        MenuDOA.retrieveMenu(0).forEach(e -> System.out.println(e.getName()));


        HibernateUtil.shutdown();
    }
}