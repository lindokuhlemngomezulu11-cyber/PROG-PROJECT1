/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Student
 */
public class Messages {
    
//Declar
import java.util.Random;
import java.util.ArrayList;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

private String messageID;
private int messageNumber;
private String recipient;
private String content;
private String messageHash;
private static final String FILE_NAME = "messages.json";
private static int totalMessagesSent = 0;
// Constructor
public Message(int messageNumber, String recipient, String content) {
Randomrandom=newRandom();
long randomID = 1000000000L
+ (long) (random.nextDouble() * 9000000000L);
this.messageID = String.valueOf(randomID);
this.messageNumber = messageNumber;
this.recipient = recipient;
this.content = content;
this.messageHash = createMessageHash();
}
// Empty constructor for Gson
public Message() {
}
// Check that the message ID contains 10 digits
public boolean checkMessageID() {
return messageID != null && messageID.matches("\\d{10}");
}
// Check the recipient cellphone number
public boolean checkRecipientCell() {
return recipient != null && recipient.matches("\\+27\\d{9}");
}
// Create the message hash
public String createMessageHash() {
if (messageID == null || content == null || content.trim().isEmpty())
{
return "";
}
String[] words = content.trim().split("\\s+");
String firstWord = words[0];
String lastWord = words[words.length- 1];
messageHash = messageID.substring(0, 2) + ":"
+ messageNumber +":"
+ firstWord.toUpperCase()
+ lastWord.toUpperCase();
return messageHash;
}
// Return message details when sent
public String SentMessage() {
totalMessagesSent++;
return "Message ID: " + messageID
+ "\nMessage Hash: " + createMessageHash()
+ "\nRecipient: " + recipient
+ "\nMessage: " + content;
}
// Display message details
public String printMessages() {
return "Message ID: " + messageID
+ "\nMessage Hash: " + createMessageHash()
+ "\nRecipient: " + recipient
+ "\nMessage: " + content;
}
// Save the message to a JSON file
public void storeMessage() {
try {
Gson gson = newGson();
ArrayList<Message> messages = readMessages();
messages.add(this);
FileWriter writer = new FileWriter(FILE_NAME);
gson.toJson(messages, writer);
writer.close();
} catch (Exception e) {
System.out.println("Could not store the message.");
}
}
// Read messages already stored
private static ArrayList<Message> readMessages() {
ArrayList<Message> messages = new ArrayList<>();
File file = new File(FILE_NAME);
if (file.exists() && file.length() > 0) {
try {
Gson gson = newGson();
FileReader reader = new FileReader(file);
Type type = new TypeToken<ArrayList<Message>>() {
}.getType();
ArrayList<Message> savedMessages =
gson.fromJson(reader, type);
reader.close();
if (savedMessages != null) {
messages = savedMessages;
}
} catch (Exception e) {
System.out.println("Could not read stored messages.");
}
}
return messages;
}
// Return the number of messages stored in JSON
public int returnTotalMessages() {
return readMessages().size();
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
    
}
