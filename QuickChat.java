package com.mycompany.quickchatpart3;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class QuickChat {

    private static final String JSON_FILE = "messages.json";
    private static final Gson GSON = new Gson();

    // Arrays required by the assignment.
    public static Message[] sentMessages = new Message[0];
    public static Message[] disregardedMessages = new Message[0];
    public static Message[] storedMessages = new Message[0];
    public static String[] messageHashes = new String[0];
    public static String[] messageIDs = new String[0];

    private QuickChat() {
    }

    public static void startQuickChat(Scanner myInputs) {
        loadStoredMessagesFromJson();
        int numberOfMessages = readMessageCount(myInputs);
        int messagesEntered = 0;
        int messageNumber = 1;

        while (true) {
            System.out.println("\n===== QUICKCHAT MENU =====");
            System.out.println("1. Send Messages");
            System.out.println("2. Show recently sent messages");
            System.out.println("3. Quit");
            System.out.println("4. Stored Messages");
            System.out.print("Choose an option: ");

            String choice = myInputs.nextLine();

            if (choice.equals("1")) {
                if (messagesEntered >= numberOfMessages) {
                    System.out.println("You have entered all your messages.");
                    continue;
                }

                while (messagesEntered < numberOfMessages) {
                    System.out.println("\n===== NEW MESSAGE =====");
                    System.out.print("Enter recipient cellphone (+27XXXXXXXXX): ");
                    String recipient = myInputs.nextLine();

                    System.out.print("Enter your message (1-249 characters): ");
                    String content = myInputs.nextLine();

                    Message message = new Message(messageNumber, "Developer", recipient, content);

                    if (!message.checkMessageID()) {
                        System.out.println("Invalid message ID. Please try again.");
                        continue;
                    }
                    if (!message.checkRecipientCell()) {
                        System.out.println("Invalid recipient cellphone number. Please try again.");
                        continue;
                    }
                    if (!message.checkMessageLength()) {
                        System.out.println("Message must contain between 1 and 249 characters.");
                        continue;
                    }

                    System.out.println("\nMessage details:");
                    System.out.println(message.printMessages());
                    System.out.println("\n1. Send Message");
                    System.out.println("2. Disregard Message");
                    System.out.println("3. Store Message");
                    System.out.print("Choose an option: ");
                    String messageChoice = myInputs.nextLine();

                    if (messageChoice.equals("1")) {
                        message.SentMessage();
                        addMessageToArray("Sent", message);
                        System.out.println("Message sent successfully.");
                    } else if (messageChoice.equals("2")) {
                        message.setStatus("Disregarded");
                        addMessageToArray("Disregarded", message);
                        System.out.println("Message disregarded.");
                    } else if (messageChoice.equals("3")) {
                        message.setStatus("Stored");
                        addMessageToArray("Stored", message);
                        saveStoredMessagesToJson();
                        System.out.println("Message stored successfully.");
                    } else {
                        System.out.println("Invalid option. Message disregarded.");
                        message.setStatus("Disregarded");
                        addMessageToArray("Disregarded", message);
                    }

                    messagesEntered++;
                    messageNumber++;
                    System.out.println("Messages entered: " + messagesEntered + " of " + numberOfMessages);
                }
            } else if (choice.equals("2")) {
                displaySentMessages();
            } else if (choice.equals("3")) {
                System.out.println("Total messages sent: " + sentMessages.length);
                System.out.println("Goodbye!");
                break;
            } else if (choice.equals("4")) {
                storedMessagesMenu(myInputs);
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }

    private static int readMessageCount(Scanner scanner) {
        while (true) {
            System.out.print("How many messages would you like to enter? ");
            String input = scanner.nextLine();
            try {
                int count = Integer.parseInt(input);
                if (count >= 0) {
                    return count;
                }
            } catch (NumberFormatException ignored) {
                // Show the message below and ask again.
            }
            System.out.println("Please enter a whole number that is zero or greater.");
        }
    }

    private static void addMessageToArray(String status, Message message) {
        message.setStatus(status);

        if ("Sent".equals(status)) {
            sentMessages = append(sentMessages, message);
        } else if ("Disregarded".equals(status)) {
            disregardedMessages = append(disregardedMessages, message);
        } else if ("Stored".equals(status)) {
            storedMessages = append(storedMessages, message);
        }

        messageHashes = append(messageHashes, message.getMessageHash());
        messageIDs = append(messageIDs, message.getMessageID());
    }

    private static Message[] append(Message[] array, Message item) {
        Message[] result = Arrays.copyOf(array, array.length + 1);
        result[array.length] = item;
        return result;
    }

    private static String[] append(String[] array, String item) {
        String[] result = Arrays.copyOf(array, array.length + 1);
        result[array.length] = item;
        return result;
    }

    public static void storedMessagesMenu(Scanner scanner) {
        while (true) {
            System.out.println("\n===== STORED MESSAGES =====");
            System.out.println("1. Display sender and recipient of stored messages");
            System.out.println("2. Display the longest stored message");
            System.out.println("3. Search by message ID");
            System.out.println("4. Search messages by recipient");
            System.out.println("5. Delete a stored message using its hash");
            System.out.println("6. Display message report");
            System.out.println("7. Return to main menu");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    displayStoredSenderAndRecipient();
                    break;
                case "2":
                    Message longest = findLongestStoredMessage();
                    System.out.println(longest == null
                            ? "There are no stored messages."
                            : longest.printMessages());
                    break;
                case "3":
                    System.out.print("Enter message ID: ");
                    Message found = findByMessageID(scanner.nextLine().trim());
                    System.out.println(found == null
                            ? "Message ID not found."
                            : "Recipient: " + found.getRecipient()
                                    + "\nMessage: " + found.getContent());
                    break;
                case "4":
                    System.out.print("Enter recipient cellphone number: ");
                    Message[] matches = searchMessagesByRecipient(scanner.nextLine().trim());
                    if (matches.length == 0) {
                        System.out.println("No messages found for that recipient.");
                    } else {
                        for (Message message : matches) {
                            System.out.println(message.printMessages());
                            System.out.println();
                        }
                    }
                    break;
                case "5":
                    System.out.print("Enter the message hash to delete: ");
                    boolean deleted = deleteStoredMessageByHash(scanner.nextLine().trim());
                    System.out.println(deleted ? "Message successfully deleted."
                            : "No stored message was found with that hash.");
                    break;
                case "6":
                    System.out.println(generateReport());
                    break;
                case "7":
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    public static void displayStoredSenderAndRecipient() {
        if (storedMessages.length == 0) {
            System.out.println("No stored messages.");
            return;
        }
        for (Message message : storedMessages) {
            System.out.println("Sender: " + message.getSender()
                    + " | Recipient: " + message.getRecipient());
        }
    }

    public static Message findLongestStoredMessage() {
        Message longest = null;
        for (Message message : storedMessages) {
            if (longest == null || message.getContent().length() > longest.getContent().length()) {
                longest = message;
            }
        }
        return longest;
    }

    public static Message findByMessageID(String id) {
        for (Message message : allMessages()) {
            if (message.getMessageID().equals(id)) {
                return message;
            }
        }
        return null;
    }

    public static Message[] searchMessagesByRecipient(String recipient) {
        List<Message> matches = new ArrayList<>();
        for (Message message : allMessages()) {
            if (message.getRecipient().equals(recipient)
                    && ("Sent".equals(message.getStatus()) || "Stored".equals(message.getStatus()))) {
                matches.add(message);
            }
        }
        return matches.toArray(new Message[0]);
    }

    public static boolean deleteStoredMessageByHash(String hash) {
        List<Message> remaining = new ArrayList<>();
        boolean deleted = false;

        for (Message message : storedMessages) {
            if (!deleted && message.getMessageHash().equals(hash)) {
                deleted = true;
            } else {
                remaining.add(message);
            }
        }

        if (deleted) {
            storedMessages = remaining.toArray(new Message[0]);
            rebuildHashAndIDArrays();
            saveStoredMessagesToJson();
        }
        return deleted;
    }

    public static String generateReport() {
        StringBuilder report = new StringBuilder("===== MESSAGE REPORT =====\n");
        Message[] messages = allMessages();

        if (messages.length == 0) {
            return report.append("No messages available.").toString();
        }

        for (Message message : messages) {
            report.append("Message Hash: ").append(message.getMessageHash()).append('\n')
                    .append("Message ID: ").append(message.getMessageID()).append('\n')
                    .append("Recipient: ").append(message.getRecipient()).append('\n')
                    .append("Message: ").append(message.getContent()).append('\n')
                    .append("Status: ").append(message.getStatus()).append("\n\n");
        }
        return report.toString();
    }

    public static void displaySentMessages() {
        if (sentMessages.length == 0) {
            System.out.println("No sent messages.");
            return;
        }
        for (Message message : sentMessages) {
            System.out.println(message.printMessages());
            System.out.println();
        }
    }

    private static Message[] allMessages() {
        List<Message> messages = new ArrayList<>();
        messages.addAll(Arrays.asList(sentMessages));
        messages.addAll(Arrays.asList(disregardedMessages));
        messages.addAll(Arrays.asList(storedMessages));
        return messages.toArray(new Message[0]);
    }

    private static void rebuildHashAndIDArrays() {
        Message[] messages = allMessages();
        messageHashes = new String[messages.length];
        messageIDs = new String[messages.length];

        for (int i = 0; i < messages.length; i++) {
            messageHashes[i] = messages[i].getMessageHash();
            messageIDs[i] = messages[i].getMessageID();
        }
    }

    public static void loadStoredMessagesFromJson() {
        File file = new File(JSON_FILE);
        if (!file.exists() || file.length() == 0) {
            return;
        }

        try (FileReader reader = new FileReader(file)) {
            Type type = new TypeToken<ArrayList<Message>>() { }.getType();
            ArrayList<Message> loaded = GSON.fromJson(reader, type);
            if (loaded != null) {
                storedMessages = loaded.toArray(new Message[0]);
                rebuildHashAndIDArrays();
            }
        } catch (Exception e) {
            System.out.println("Could not load messages.json: " + e.getMessage());
        }
    }

    private static void saveStoredMessagesToJson() {
        try (FileWriter writer = new FileWriter(JSON_FILE)) {
            GSON.toJson(Arrays.asList(storedMessages), writer);
        } catch (Exception e) {
            System.out.println("Could not save messages.json: " + e.getMessage());
        }
    }

    // Loads the exact assignment sample data for demonstration and unit tests.
    public static void loadTestData() {
        sentMessages = new Message[0];
        disregardedMessages = new Message[0];
        storedMessages = new Message[0];
        messageHashes = new String[0];
        messageIDs = new String[0];

        addMessageToArray("Sent", new Message(1, "1000000001", "Developer",
                "+27834557896", "Did you get the cake?", "Sent"));
        addMessageToArray("Stored", new Message(2, "1000000002", "Developer",
                "+27838884567", "Where are you? You are late! I have asked you to be on time.", "Stored"));
        addMessageToArray("Disregarded", new Message(3, "1000000003", "Developer",
                "+27834484567", "Yohoooo, I am at your gate.", "Disregarded"));
        // The assignment's search test explicitly uses 0838884567 as message 4's ID.
        addMessageToArray("Sent", new Message(4, "0838884567", "Developer",
                "+27830000000", "It is dinner time!", "Sent"));
        addMessageToArray("Stored", new Message(5, "1000000005", "Developer",
                "+27838884567", "Ok, I am leaving without you.", "Stored"));
    }
}
