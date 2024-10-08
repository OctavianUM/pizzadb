package com.dbproject.dao;

import com.dbproject.domain.MenuItem;
import com.dbproject.util.HibernateUtil;
import com.dbproject.util.Querries;

public class MenuItemDAO {

    public static MenuItem getMenuItemById(int id) {
        var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession();
        try {
            return Querries.getMenuItemByID(session, id);
        } finally {
            session.close();
        }
    }

}
