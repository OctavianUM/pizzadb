package com.dbproject;

import com.dbproject.cli.HomeScreen;
import com.dbproject.dao.MenuDAO;
import com.dbproject.util.*;


public class App {

    public static void main(String[] args) {
        // initialize database connection when starting the program
        HibernateUtil.getSessionFactory();

        HomeScreen.show();

        HibernateUtil.shutdown();
    }
}
