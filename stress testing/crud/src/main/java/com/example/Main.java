package com.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Crud crud = new Crud();
        try(Scanner scanner = new Scanner(System.in);) {
            boolean isRunning = true;
            while (isRunning) {
                System.out.println("Enter something");
                System.out.println("Insert");
                System.out.println("Select all");
                System.out.println("Select by ID");
                System.out.println("Delete");
                System.out.println("Update");
                System.out.println("exit");
                int choice = Integer.parseInt(scanner.nextLine());
                switch(choice) {
                case 1:
                System.out.println("To get connected enter your name, age, and email address:");
                crud.createUser(new User(scanner.nextLine(), Integer.parseInt(scanner.nextLine()), scanner.nextLine()));
                    crud.createUser(null);
                    break;
                case 2:
                    crud.readAll();
                    break;
                case 3:
                    System.out.println("Enter the id: ");
                    crud.readById(Integer.parseInt(scanner.nextLine()));
                    break;
                case 4:
                    System.out.println("Enter the id to delete:");
                    crud.deleteById(Integer.parseInt(scanner.nextLine()));
                    break;
                case 5:
                    System.out.println("Enter the id to update ");
                    int updateId = Integer.parseInt(scanner.nextLine());
                    boolean isFound = crud.readById(updateId);
                    if (isFound) {
                        System.out.println("Enter name, age, email");
                        User user = new User(updateId, scanner.nextLine(), scanner.nextInt(), scanner.nextLine());
                        crud.updateById(user);
                    }
                    break;
                case 6:
                    System.out.println("See you later");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Something went rong please try again.");
                    break;  
                }   
            }
        } catch (Exception e) {
            throw new RuntimeException("Something went wrong " + e);
        }
    }
}