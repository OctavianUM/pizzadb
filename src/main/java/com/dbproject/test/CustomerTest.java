package com.dbproject.test;

import java.sql.Date;

import com.dbproject.dao.CustomerDAO;
import com.dbproject.domain.Customer;

public class CustomerTest {
    public static boolean test_adding_login_deleting_customer(){
        CustomerDAO customerDAO = new CustomerDAO();

        String email = "johndoe@example.com";
        String password = "johndoe";
        Customer customer = new Customer(
            "John",
            "Doe", 
            'M',
            new Date(01052005),
            12345678, 
            1, 
            email, 
            password
        );

        customerDAO.removeCustomer(email);
        if(customerDAO.customerExists(email)) 
            return false;
        
        customerDAO.saveCustomer(customer);
        if(customerDAO.customerExists(email)) System.out.println("successfully added customer");
        else return false;

        if(customerDAO.validateLogin(email, password)) System.out.println("successful login");
        else return false;
        
        customerDAO.removeCustomer(email);
        if(!customerDAO.customerExists(email)) System.out.println("successfully removed customer");
        else return false;
        
        return true;
    }
}
