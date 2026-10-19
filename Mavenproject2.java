/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject2;

/**
 *
 * @author Student
 */  
    import java.util.Scanner;
public class Mavenproject2 {
// Username
public static boolean CheckUserName(String username) {
return username.contains("_") && username.length() <= 5;
}
//Password
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
//cellPhone
public static boolean CheckCellPhone(String cellPhone) {
return cellPhone.matches("^\\+27\\d{9}$");
}
//login
public static boolean LoginUser(String username, String password, String savedUsername, String
savedPassword) {
return username.equals(savedUsername) && password.equals(savedPassword);
}
public static void main(String[] args) {
//Declarations
try (Scanner myInputs = new Scanner(System.in)) {
//Declarations
String firstName;
String lastName;
String userName;
String password;
String cellPhone;
//Prompt for registration
//Name
System.out.println("Please enter your name");
firstName = myInputs.nextLine();
//Surname
System.out.print("Please enter your surname");
lastName = myInputs.nextLine();
//UserName
while (true) {
System.out.print("Enter your username");
userName = myInputs.nextLine();
if (CheckUserName(userName)) {
System.out.println("Welcome "+ firstName + " " + lastName +", it is great to see you again.");
break;
} else {
System.out.println("Username is not correctly formatted; please ensure that yor"
+ "username contains an underscore and is no more than five caharctersin length.");
}
}
//Password
while (true) {
System.out.print("Enter your password");
password = myInputs.nextLine();
if(CheckPassword(password)) {
System.out.println("Password successfully captured");
break;
} else {
System.out.println("Password is not correctly formatted; please ensure that "
+ "the password contains at least eight characters, a capital letter,"
+ "a number, and a special character.");
}
}
//Cell Phone
while (true) {
System.out.print("Enter your cellphone number: ");
cellPhone = myInputs.nextLine();
if (CheckCellPhone(cellPhone)) {
System.out.println("Cellphone number successfully captured");
break;
} else {
System.out.println("Cell number is incorrectly formatted or does not contain an international code;" + " please correct the number and try again.");
}
}
//Registration was successful
System.out.println();
System.out.println("Registration successful!");
//Login
String loginUsername;
String loginPassword;
System.out.print("Enter your username: ");
loginUsername = myInputs.nextLine();
System.out.print("Enter your password: ");
loginPassword = myInputs.nextLine();
if (LoginUser(loginUsername, loginPassword, userName, password)) {
System.out.println("True");
System.out.println("Welcome to QuickChat.");
QuickChat.startQuickChat(myInputs);
} else {
System.out.println("False.");
}
}
}
}
