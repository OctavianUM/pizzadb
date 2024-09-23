package org.pizzadb;
import org.pizzadb.config.DatabaseConnection;
import org.pizzadb.model.Adress;
import org.pizzadb.repository.AdressRepository;
import org.pizzadb.service.AdressService;

import java.util.LinkedList;
import java.util.List;


//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        DatabaseConnection databaseConnection = new DatabaseConnection("jdbc:mysql://localhost:3306/pizzadb", "root", "octavian007");
        databaseConnection.connect();

        AdressService adressService = new AdressService(databaseConnection);
        Adress randomAdress = adressService.getAdressByID(2);

        System.out.println(randomAdress.getStreet());

    }
}