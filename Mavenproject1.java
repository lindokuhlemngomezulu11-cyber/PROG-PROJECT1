/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject1;

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class Mavenproject1 {

public static boolean CheckUserName(String username) {
return username.contains("_") && username.length() <= 5;
}
public static boolean CheckPassword(String password) {
boolean hasUppercase = false;
boolean hasNumber = false;
boolean hasSpecial = false;
for (int i = 0; i < password.length(); i++) {
char character = password.charAt(i);
if (Character.isUpperCase(character)) {
hasUppercase = true;
}
if (Character.isDigit(character)) {
hasNumber = true;
}
if (!Character.isLetterOrDigit(character)) {
hasSpecial = true;
}
}
return password.length() >= 8
&&hasUppercase &&hasNumber &&hasSpecial;
}
public static boolean CheckCellPhone(String cellPhone) {
return cellPhone.matches("\\+27\\d{9}");
}
public static boolean LoginUser(String username, String password,
String registeredUsername,
String registeredPassword) {
return username.equals(registeredUsername)
&&password.equals(registeredPassword);
}
public static void main(String[] args) {
Scanner myInputs = new Scanner(System.in);
System.out.println("===== REGISTRATION =====");
System.out.print("Enter your first name: ");
String firstName = myInputs.nextLine();
System.out.print("Enter your last name: ");
String lastName = myInputs.nextLine();
System.out.print("Enter your username: ");
String username = myInputs.nextLine();
if (!CheckUserName(username)) {
System.out.println(
"Username is not correctly formatted. "
+ "It must contain an underscore and be 5 characters or less."
);
myInputs.close();
return;
}
System.out.print("Enter your password: ");
String password = myInputs.nextLine();
if (!CheckPassword(password)) {
System.out.println(
"Password must have at least 8 characters, "
+ "an uppercase letter, a number and a special character."
);
myInputs.close();
return;
}
System.out.print("Enter your cellphone number (+27XXXXXXXXX): ");
String cellPhone = myInputs.nextLine();
if (!CheckCellPhone(cellPhone)) {
System.out.println("Invalid cellphone number.");
myInputs.close();
return;
}
System.out.println("\nRegistration successful!");
System.out.println("\n===== LOGIN =====");
System.out.print("Enter your username: ");
String loginUsername = myInputs.nextLine();
System.out.print("Enter your password: ");
String loginPassword = myInputs.nextLine();
if (LoginUser(loginUsername, loginPassword, username, password)) {
System.out.println("Welcome " + firstName + " " + lastName
+ ". It is great to see you again.");
System.out.println("Welcome to QuickChat.");
sendmessages.startQuickChat(myInputs);
} else {
System.out.println("Username or password incorrect.");
}
myInputs.close();
}
}
   
   
