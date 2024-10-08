package com.dbproject;

import com.dbproject.dao.DiscountDAO;
import com.dbproject.util.*;


public class App {

    public static void main(String[] args) {
        // initialize database connection when starting the program
        HibernateUtil.getSessionFactory();

        System.out.println(DiscountDAO.getDiscountCodeByString("OFF10").getDiscount());

        HibernateUtil.shutdown();
    }
}