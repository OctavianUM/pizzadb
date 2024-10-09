package com.dbproject.dao;



import com.dbproject.domain.Adress;
import com.dbproject.domain.Customer;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.PasswordEncrypt;
import com.dbproject.util.Querries;
public class CustomerDAO {

    public boolean customerExists(String email){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            Customer c = Querries.getCustomerByEmail(session, email);
            return c != null;
        } finally {
            session.close();
        }
        
    }

    public static int getPizzasOrderedByCustomerById(int id){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getPizasOrderedByCustomerId(session, id);
        } finally {
            session.close();
        }
    }

    // Method to validate login
    public int validateLogin(String email, String password){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();

        try {
            Customer customer = Querries.getCustomerByEmail(session, email);

            if (customer != null &&
                PasswordEncrypt.checkPassword(password, customer.getPassword())){
                // Step 2: Validate password with BCrypt
                return customer.getCustomerId();
            } else {
                return Integer.MIN_VALUE;
            }
        } finally {
            session.close();
        }
    }

    // Method to save a customer with a hashed password
    public void saveCustomer(Customer customer) {
        HibernateUtil.getSessionFactory().inTransaction(session ->{
            customer.setPassword( 
                PasswordEncrypt.hashPassword(customer.getPassword())
            );
            session.persist( customer);
        });
    }
    
    // Method to delete a customer with a hashed password
    public void removeCustomer(String email) {
        if(customerExists(email)){
            HibernateUtil.getSessionFactory().inTransaction(session ->{
                session.remove(Querries.getCustomerByEmail(session, email));
            });
        }
    }

    public static Customer getCustomerById(int id){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getCustomerById(session, id);
        } finally {
            
            session.close();
        }
    }

    

    public static Adress getCustomerAdress(int id){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try { 
            return Querries.getCustomerAdress(session, id);
        } finally {
            
            session.close();
        }
    }
}
