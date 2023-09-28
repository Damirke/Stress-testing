package com.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    private static final String DRIVER_PATH = "com.mysql.cj.jdbc.Driver";
    private static final String DRIVER_URL = "jdbc:mysql://localhost:3306/users";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Olgen_Shybyn";
    public Database() {
        try {
            Class.forName(DRIVER_PATH);
        } catch (Exception e){
            throw new RuntimeException("Something went wrong with the database:" + e);
        }
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DRIVER_URL, USERNAME, PASSWORD);
    }
}