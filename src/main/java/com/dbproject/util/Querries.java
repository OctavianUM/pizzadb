package com.dbproject.util;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import com.dbproject.domain.Adress;
import com.dbproject.domain.MenuItem;

public class Querries {
    public static Adress getAdressByStreet(Session session, String street){
        return session.createNamedQuery("select_adress_by_street", Adress.class)
                .setParameter("street", "Paul-Henri Spaaklaan")
                .getSingleResult();
    }

    public static List<MenuItem> getMenuById(Session session, int id){
        return session.createQuery(
                "SELECT mi "+
                "FROM Menu m "+
                "LEFT JOIN MenuItem mi ON m.menuItemId = mi.menuItemId "+
                "WHERE m.menuId = :id"
                , MenuItem.class)
            .setParameter("id", id)
            .getResultList();
    }
  

}
