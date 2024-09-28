package com.dbproject;

import com.dbproject.dao.CustomerDAO;
import com.dbproject.domain.Adress;
import com.dbproject.domain.Customer;
import com.dbproject.domain.MenuItem;
import com.dbproject.util.*;

import java.sql.Date;
import java.util.*;
import org.hibernate.Session;



public class App {
    
    public static void main(String[] args) {
        //initialize database connection when starting the program
        HibernateUtil.getSessionFactory();

        CustomerDAO customerDAO = new CustomerDAO();

        String email = "johndoe@example.com";
        String password = "johndoe";
        
        if (customerDAO.customerExists(email)) {
            System.out.println(
                customerDAO.validateLogin(email, password)? "successfull login" : "wrong password"
            );
            
        } else {    
            System.out.println("customer does not exist: creating new one");
            Customer customer = new Customer("John", "Doe", 'M',new Date(01052005), 12345678, 1, email, password);
    
            customerDAO.saveCustomer(customer);
        }

        HibernateUtil.shutdown();
    }
}