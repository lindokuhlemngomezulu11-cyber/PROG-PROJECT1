/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Student
 */
import java.util.Scanner;

public class sendmessages {
public static void startQuickChat(Scanner myInputs) {
System.out.print("How many messages would you like to enter? ");
int numberOfMessages = myInputs.nextInt();
myInputs.nextLine();
int messagesEntered = 0;
int messageNumber = 1;
while (true) {
System.out.println("\n===== QUICKCHAT MENU =====");
System.out.println("1. Send Messages");
System.out.println("2. Show recently sent messages");
System.out.println("3. Quit");
System.out.print("Choose an option: ");
String choice = myInputs.nextLine();
if (choice.equals("1")) {
while (messagesEntered < numberOfMessages) {
System.out.println("\n===== NEW MESSAGE =====");
System.out.print("Enter recipient cellphone (+27XXXXXXXXX:)");
String recipient = myInputs.nextLine();
System.out.print("Enter your message: ");
String content = myInputs.nextLine();
Messages message = new Message(
messageNumber, recipient, content
);
if (!message.checkMessageID()) {
System.out.println("Invalid message ID.");
continue;
}
if (!message.checkRecipientCell()) {
System.out.println("Invalid recipient cellphone number.");
continue;
}
if (content.trim().isEmpty() || content.length() >= 250) {
System.out.println( "Message must contain between 1 and 249 characters.");
}
continue;
System.out.println("\nMessage details:");
System.out.println("Message ID: " +
message.getMessageID());
System.out.println("Message Hash: " +
message.createMessageHash());
System.out.println("\n1. Send Message");
System.out.println("2. Disregard Message");
System.out.println("3. Store Message");
System.out.print("Choose an option: ");
String messageChoice = myInputs.nextLine();
if (messageChoice.equals("1")) {
System.out.println(message.SentMessage());
System.out.println("Message sent successfully.");
} else if (messageChoice.equals("2")) {
System.out.println("Message disregarded.");
} else if (messageChoice.equals("3")) {
message.storeMessage();
System.out.println("Message storage requested.");
} else {
System.out.println("Invalid option. Message disregarded.");
}
messagesEntered++;
messageNumber++;
System.out.println("Messages entered: "
+ messagesEntered + " of " + numberOfMessages);
}
if (messagesEntered >= numberOfMessages) {
System.out.println("You have entered all your messages.");
}
} else if (choice.equals("2")) {
System.out.println("Coming Soon.");
} else if (choice.equals("3")) {
System.out.println("Goodbye!");
break;
} else {
System.out.println("Invalid option. Please try again.");
}
}
}
}
    

