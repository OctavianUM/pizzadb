package com.dbproject.dao;


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

    // Method to validate login
    public boolean validateLogin(String email, String password){
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();

        try {
            Customer customer = Querries.getCustomerByEmail(session, email);

            if (customer != null) {
                // Step 2: Validate password with BCrypt
                return PasswordEncrypt.checkPassword(password, customer.getPassword());
            } else {
                return false;
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
}
