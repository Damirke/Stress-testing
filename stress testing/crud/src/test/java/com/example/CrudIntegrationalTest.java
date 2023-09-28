package com.example;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class UserCrudIntegrationTest {
    private UserCrud userCrud;
    private Connection connection;
    private DatabaseConnection databaseConnection;

    @Before
    public void setUp() throws SQLException {
        userCrud = new UserCrud();

        databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    @After
    public void tearDown() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    @Test
    public void integrationTestCreateUser() throws SQLException {
        User user = new User("packman", 22, "haha@gmail.com");
        userCrud.createUser(user);
        assertTrue(userCrud.readById(user.getId()));
    }

    @Test
    public void integrationTestDeleteById() throws SQLException {
        User user = new User("packman", 22, "haha@gmail.com");
        userCrud.createUser(user);
        userCrud.deleteById(user.getId());
        assertFalse(userCrud.readById(user.getId()));
    }

    @Test
    public void integrationTestReadAll() throws SQLException {
        User user1 = new User("packman", 22, "haha@gmail.com");
        userCrud.createUser(user1);
        User user2 = new User("mario", 21, "oink@gmail.com");
        userCrud.createUser(user2);
        User user3 = new User("vanila", 13, "ice@gmail.com");
        userCrud.createUser(user3);
        userCrud.readAll();
        int numberOfUsers = countUsers();
        assertEquals(3, numberOfUsers);
    }

    private int countUsers() throws SQLException {
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(USER_ID) FROM USER");
             ResultSet resultSet = preparedStatement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getInt(1);
            }
        }
        return 0;
    }

    @Test
    public void integrationTestReadById() throws SQLException {
        User user = new User("packman", 22, "haha@gmail.com");
        userCrud.createUser(user);

        boolean userFound = userCrud.readById(user.getId());

        assertTrue(userFound);
    }

    @Test
    public void integrationTestReadByIdNoUser() throws SQLException {
        boolean userNotFound = userCrud.readById(-1);
        assertFalse(userNotFound);
    }

    @Test
    public void integrationTestUpdateById() throws SQLException {
        User user = new User("packman", 22, "haha@gmail.com");
        userCrud.createUser(user);
        User retrievedUser = getUserById(user.getId());
        assertNotNull(retrievedUser);
        User updatedUser = new User(user.getId(), "packman", 24, "hahaha@gmail.com");
        userCrud.updateById(updatedUser);

        retrievedUser = getUserById(user.getId());
        assertNotNull(retrievedUser);
        assertEquals(updatedUser.getNickname(), retrievedUser.getNickname());
        assertEquals(updatedUser.getAge(), retrievedUser.getAge());
        assertEquals(updatedUser.getAddress(), retrievedUser.getAddress());
    }

    private User getUserById(int id) throws SQLException {
        User user = null;
        try (Connection connection = databaseConnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(Query.readByIdQuery(id))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User(
                            resultSet.getInt("USER_ID"),
                            resultSet.getString("USER_NICKNAME"),
                            resultSet.getInt("USER_AGE"),
                            resultSet.getString("USER_EMAIL")
                    );
                }
            }
        }
        return user;
    }
}
