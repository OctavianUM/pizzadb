package com.dbproject.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import static org.hibernate.cfg.JdbcSettings.FORMAT_SQL;
import static org.hibernate.cfg.JdbcSettings.HIGHLIGHT_SQL;
import static org.hibernate.cfg.JdbcSettings.JAKARTA_JDBC_DRIVER;
import static org.hibernate.cfg.JdbcSettings.JAKARTA_JDBC_PASSWORD;
import static org.hibernate.cfg.JdbcSettings.JAKARTA_JDBC_URL;
import static org.hibernate.cfg.JdbcSettings.JAKARTA_JDBC_USER;
import static org.hibernate.cfg.JdbcSettings.SHOW_SQL;

import com.dbproject.domain.*;
 
public class HibernateUtil {
 
    private static final SessionFactory sessionFactory = buildSessionFactory();
 
    private static SessionFactory buildSessionFactory() {
        try {
            SessionFactory sf = new Configuration()
                .addAnnotatedClass(Recipe.class)
                .addAnnotatedClass(Adress.class)
                .addAnnotatedClass(Courier.class)
                .addAnnotatedClass(Customer.class)
                .addAnnotatedClass(Delivery.class)
                .addAnnotatedClass(DiscountCode.class)
                .addAnnotatedClass(Ingredient.class)
                .addAnnotatedClass(Menu.class)
                .addAnnotatedClass(MenuItem.class)
                .addAnnotatedClass(Order.class)
                .addAnnotatedClass(OrderItem.class)
                // use database
                .setProperty(JAKARTA_JDBC_URL, "jdbc:mysql://localhost:3306/pizzadb")
                .setProperty(JAKARTA_JDBC_USER, "root")
                .setProperty(JAKARTA_JDBC_PASSWORD, "password")
                .setProperty(JAKARTA_JDBC_DRIVER, "com.mysql.cj.jdbc.Driver")
                // use Agroal connection pool
                .setProperty("hibernate.agroal.maxSize", 20)
                // display SQL in console
                .setProperty(SHOW_SQL, false)
                .setProperty(FORMAT_SQL, true)
                .setProperty(HIGHLIGHT_SQL, true)
                .buildSessionFactory();
            System.out.println("\nDATABASE CONNECTION SUCCESSFUL\n");
            return sf;
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
 
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    public static void shutdown() {
        // Close caches and connection pools
        getSessionFactory().close();
    }
 
}