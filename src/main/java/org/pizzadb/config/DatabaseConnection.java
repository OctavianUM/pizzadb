package org.pizzadb.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private String sqlSelectAllPersons = "SELECT * FROM person";
    private String connectionUrl;
    private String username;
    private String password;
    private Connection connection;

    public DatabaseConnection(String connectionUrl, String username, String password) {
        this.connectionUrl = connectionUrl;
        this.username = username;
        this.password = password;
}

    public void connect() {
        try {
            connection = DriverManager.getConnection(connectionUrl, username, password);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Connection getConnection() {
        return connection;
    }

}
