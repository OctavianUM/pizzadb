package org.pizzadb.repository;

import org.pizzadb.config.DatabaseConnection;
import org.pizzadb.model.Adress;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AdressRepository {

    private final String sqlSelectAllAdresses = "SELECT * FROM adress";
    private final DatabaseConnection databaseConnection;

    public AdressRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public List<Adress> getAllAdresses() {

        List<Adress> adressList = new LinkedList<>();

        try (Connection conn = databaseConnection.getConnection();
             PreparedStatement ps = conn.prepareStatement(sqlSelectAllAdresses);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                long id = rs.getLong("adressId");
                short postal = rs.getShort("postal");
                String street = rs.getString("street");
                int number = rs.getInt("number");

                Adress adress = new Adress(id, postal, street, number);
                adressList.add(adress);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return adressList;
    }

}
