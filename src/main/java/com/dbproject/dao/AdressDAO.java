package com.dbproject.dao;

import com.dbproject.domain.Adress;
import com.dbproject.domain.Order;
import com.dbproject.util.HibernateUtil;

public class AdressDAO {
public static int createAdress(Adress adress){
        HibernateUtil.getSessionFactory().inTransaction(session ->{
            session.persist(adress);
        });
        return adress.getId();
    }
}
