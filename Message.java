/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject2;

/**
 *
 * @author Student
 */

import java.util.Random;
import java.util.ArrayList;
public class Message {
private String messageID;
private int messageNumber;
private String recipient;
private String content;
private String messageHash;
private static int totalMessages = 0;
private static ArrayList<Message> sentMessages = new ArrayList<>();
private static ArrayList<Message> storedMessages = new ArrayList<>();
// Constructor
public Message(int messageNumber, String recipient, String content) {
Randomrandom= newRandom();
long randomID = 1000000000L
+ (long) (random.nextDouble() * 9000000000L);
this.messageID = String.valueOf(randomID);
this.messageNumber = messageNumber;
this.recipient = recipient;
this.content = content;
this.messageHash = createMessageHash();
}
// Check that the message ID has 10 digits
public boolean checkMessageID() {
return messageID.matches("\\d{10}");
}
// Check the recipient cellphone number
public boolean checkRecipientCell() {
return recipient.matches("\\+27\\d{9}");
}
// Create the message hash
public String createMessageHash() {
if (content == null || content.trim().isEmpty()) {
return "";
}
String[] words = content.trim().split("\\s+");
String firstWord = words[0];
String lastWord = words[words.length- 1];
messageHash = messageID.substring(0, 2) + ":"
+ messageNumber + ":"
+ firstWord.toUpperCase()
+ lastWord.toUpperCase();
return messageHash;
}
// Send the message
public String SentMessage() {
sentMessages.add(this);
totalMessages++;
return "Message sent successfully.";
}
// Display message details
public String printMessages() {
return "Message ID: " + messageID
+ "\nMessage Hash: " + createMessageHash()
+ "\nRecipient: " + recipient
+ "\nMessage: " + content;
}
// Store the message temporarily in memory
public void storeMessage() {
storedMessages.add(this);
}
// Return the total number of messages sent
public static int returnTotalMessages() {
return totalMessages;
}
// Display all sent messages
public static void displaySentMessages() {
if (sentMessages.isEmpty()) {
System.out.println("No messages have been sent yet.");
} else {
for (Message message : sentMessages) {
System.out.println(message.printMessages());
System.out.println();
}
}
}
// Getters
public String getMessageID() {
return messageID;
}
public int getMessageNumber() {
return messageNumber;
}
public String getRecipient() {
return recipient;
}
public String getContent() {
return content;
}
public String getMessageHash() {
return messageHash;
}
}
