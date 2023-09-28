package com.example;
public class Query {
    public static String createUserQuery() {
        return "INSERT INTO USER (USER_NICKNAME, USER_AGE, USER_EMAIL) VALUES (?, ? , ?)";
    }

    public static String readAllQuery() {
        return "SELECT * FROM USER";
    }

    public static String readByIdQuery(int id) {
        return "SELECT * FROM USER WHERE USER_ID = " + id;
    }
    public static String deleteByIdQuery(int id) {
        return "DELETE * FROM USER WHERE USER_ID = "+ id;
    }
    public static String updateByIdQuery(int id) {
        return "UPDATE USER SET USER_NICKNAME = ?, USER_AGE = ?, USER_EMAIL = ? WHERE USER_ID = " + id;
    }
}
