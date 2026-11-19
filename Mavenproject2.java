package com.mycompany.quickchatpart3;

import java.util.Scanner;

public class Mavenproject2 {

    public static boolean CheckUserName(String username) {
        return username.contains("_") && username.length() <= 5;
    }

    public static boolean CheckPassword(String password) {
        boolean hasCapital = false;
        boolean hasSpecial = false;
        boolean hasNumber = false;

        if (password.length() < 8) {
            return false;
        }

        for (int i = 0; i < password.length(); i++) {
            char character = password.charAt(i);

            if (Character.isUpperCase(character)) {
                hasCapital = true;
            }
            if (Character.isDigit(character)) {
                hasNumber = true;
            }
            if (!Character.isLetterOrDigit(character)) {
                hasSpecial = true;
            }
        }

        return hasCapital && hasNumber && hasSpecial;
    }

    public static boolean CheckCellPhone(String cellPhone) {
        return cellPhone.matches("^\\+27\\d{9}$");
    }

    public static boolean LoginUser(String username, String password,
                                    String savedUsername, String savedPassword) {
        return username.equals(savedUsername) && password.equals(savedPassword);
    }

    public static void main(String[] args) {
        try (Scanner myInputs = new Scanner(System.in)) {
            String firstName;
            String lastName;
            String userName;
            String password;
            String cellPhone;

            System.out.print("Please enter your name: ");
            firstName = myInputs.nextLine();

            System.out.print("Please enter your surname: ");
            lastName = myInputs.nextLine();

            while (true) {
                System.out.print("Enter your username: ");
                userName = myInputs.nextLine();

                if (CheckUserName(userName)) {
                    System.out.println("Welcome " + firstName + " " + lastName
                            + ", it is great to see you again.");
                    break;
                } else {
                    System.out.println("Username is not correctly formatted. "
                            + "It must contain an underscore and be no more than five characters.");
                }
            }

            while (true) {
                System.out.print("Enter your password: ");
                password = myInputs.nextLine();

                if (CheckPassword(password)) {
                    System.out.println("Password successfully captured.");
                    break;
                } else {
                    System.out.println("Password must contain at least eight characters, "
                            + "a capital letter, a number and a special character.");
                }
            }

            while (true) {
                System.out.print("Enter your cellphone number (+27XXXXXXXXX): ");
                cellPhone = myInputs.nextLine();

                if (CheckCellPhone(cellPhone)) {
                    System.out.println("Cellphone number successfully captured.");
                    break;
                } else {
                    System.out.println("Cellphone number is incorrectly formatted. "
                            + "Use +27 followed by nine digits.");
                }
            }

            System.out.println("\nRegistration successful!");

            System.out.print("Enter your username to log in: ");
            String loginUsername = myInputs.nextLine();

            System.out.print("Enter your password to log in: ");
            String loginPassword = myInputs.nextLine();

            if (LoginUser(loginUsername, loginPassword, userName, password)) {
                System.out.println("Login successful.");
                QuickChat.startQuickChat(myInputs);
            } else {
                System.out.println("Username or password incorrect.");
            }
        }
    }
}
