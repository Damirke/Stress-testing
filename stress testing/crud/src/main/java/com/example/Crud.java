package com.example;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Crud {
    Database database = new Database();
    public void createUser(User user) throws SQLException {
        try(Connection connection = database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Query.createUserQuery());) {
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getAddress());

            int rows = preparedStatement.executeUpdate();
            if(rows > 0) {
                System.out.println("Succesfull");
            } else {
                System.out.println("Something went wrong. Try to create again");
            }
        }
    }

    public void readAll() throws SQLException {
        try(Connection connection = database.getConnection();
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(Query.readAllQuery());) {
            while(resultSet.next()) {
                readUser(new User(resultSet.getInt("USER_ID"), resultSet.getString("USER_NICKNAME"), resultSet.getInt("USER_AGE"), resultSet.getString("USER_EMAIL")));
            }  
        }
    }
    public void readUser(User user) {
        System.out.println("ID: " + user.getId());
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("Age: " + user.getAge());
        System.out.println("email: " + user.getAddress());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++");
    }
    public boolean readById(int id) throws SQLException {
        boolean isFound = false;
        try(Connection connection = database.getConnection();
        Statement statement = connection.createStatement()) {
            User user = new User();
            user.setId(id);
            try (ResultSet resultSet = statement.executeQuery(Query.readByIdQuery(id))) {
                if(resultSet.next()) {
                    isFound = true;
                    readUser(new User(resultSet.getInt("USER_ID"), resultSet.getString("USER_NICKNAME"), resultSet.getInt("USER_AGE"), resultSet.getString("USER_EMAIL")));
                } else {
                System.out.println("Result are not found for the id " + id);
                }
            }
        }
        return isFound;
    }
    
    public void deleteById(int id) throws SQLException {
        try(Connection connection = database.getConnection();
        Statement statement = connection.createStatement();) {
            int rows = statement.executeUpdate(Query.deleteByIdQuery(id));
            if (rows > 0) {
                System.out.println("User deleted succesfully");
            } else {
                System.out.println("Something went wrong. Please try to delete the user again");
            }
        }
    }
    
    public void updateById(User user) throws SQLException {
        try(Connection connection = database.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(Query.updateByIdQuery(user.getId()));) {
            preparedStatement.setString(1, user.getNickname());
            preparedStatement.setInt(2, user.getAge());
            preparedStatement.setString(3, user.getAddress());

            int rows = preparedStatement.executeUpdate();
            if(rows > 0) {
                System.out.println("Succesfull");
            } else {
                System.out.println("Something went wrong. Try to update again");
            }
        }
    }
}
