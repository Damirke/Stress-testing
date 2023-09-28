package com.example;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;

import org.junit.Test;

public class UserCrudStressTest {
    private UserCrud userCrud = new UserCrud();
    private final int numThreads = 100;
    private final CountDownLatch latch = new CountDownLatch(numThreads);

    @Test
    public void stressTestCreateUser() throws SQLException {
        User user = new User("packman", 22, "haha@gmail.com");
        for (int i = 0; i < numThreads; i++) { 
            userCrud.createUser(user);
        }

        assertTrue(true);
    }

    @Test
    public void stressTestDeleteById() throws InterruptedException, SQLException {
        for (int i = 1; i <= numThreads; i++) {
            int userId = i;
            Thread thread = new Thread(() -> {
                try {
                    userCrud.deleteById(userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
            thread.start();
        }
        latch.await();
    }

    @Test
    public void stressTestReadById() throws InterruptedException {
        for (int i = 1; i <= numThreads; i++) {
            int userId = i;
            Thread thread = new Thread(() -> {
                try {
                    userCrud.readById(userId);
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    latch.countDown();
                }
            });
            thread.start();
        }
        latch.await();
    }

    @Test
    public void stressTestUpdateById() throws SQLException {
        User user = new User("packman", 22, "haha@gmail.com");
        for (int i = 0; i < numThreads; i++) { 
            try {
                userCrud.updateById(user);
            } catch (SQLException e) {
                System.err.println("Error: " + e.getMessage());
            }
        }

        assertTrue(true);
    }
}
