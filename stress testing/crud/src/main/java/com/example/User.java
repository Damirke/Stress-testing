package com.example;

public class User {
    private int id;
    private String nickname;
    private int age;
    private String address;

     public User(int id, String nickname, int age, String address) {
        this.id = id;
        this.nickname = nickname;
        this.age = age;
        this.address = address;
    }
    public User(String nickname, int age, String address) {
        this.nickname = nickname;
        this.age = age;
        this.address = address;
    }

    public User() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public int getAge() {
        return age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
}
