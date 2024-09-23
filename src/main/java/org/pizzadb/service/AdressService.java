package org.pizzadb.service;

import org.pizzadb.config.DatabaseConnection;
import org.pizzadb.model.Adress;
import org.pizzadb.repository.AdressRepository;

import java.util.List;

public class AdressService {

    private final AdressRepository adressRepository;

    public AdressService(DatabaseConnection databaseConnection) {
        adressRepository = new AdressRepository(databaseConnection);
    }

    public Adress getAdressByID(long id) {
        List<Adress> list = adressRepository.getAllAdresses();
        for (int index = 0; index<list.size(); index++) {
            if (list.get(index).getId() == id)
                return list.get(index);
        }
        return new Adress(0L, (short) 0, "null", 0);
    }

}
