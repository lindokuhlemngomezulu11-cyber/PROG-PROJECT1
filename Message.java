package com.mycompany.quickchatpart3;

import java.util.Random;

public class Message {

    private String messageID;
    private int messageNumber;
    private String sender;
    private String recipient;
    private String content;
    private String messageHash;
    private String status;

    // Constructor for messages entered by a user.
    public Message(int messageNumber, String sender, String recipient, String content) {
        this(messageNumber, createRandomID(), sender, recipient, content, "Pending");
    }

    // Constructor also allows the supplied assignment test ID to be used.
    public Message(int messageNumber, String messageID, String sender,
                   String recipient, String content, String status) {
        this.messageNumber = messageNumber;
        this.messageID = messageID;
        this.sender = sender;
        this.recipient = recipient;
        this.content = content;
        this.status = status;
        this.messageHash = createMessageHash();
    }

    // No-argument constructor is needed by Gson when reading JSON.
    public Message() {
    }

    private static String createRandomID() {
        Random random = new Random();
        long id = 1000000000L + (long) (random.nextDouble() * 9000000000L);
        return String.valueOf(id);
    }

    public boolean checkMessageID() {
        return messageID != null && messageID.matches("\\d{10}");
    }

    public boolean checkRecipientCell() {
        return recipient != null && recipient.matches("\\+27\\d{9}");
    }

    public boolean checkMessageLength() {
        return content != null && !content.trim().isEmpty() && content.length() < 250;
    }

    public String createMessageHash() {
        if (messageID == null || messageID.length() < 2
                || content == null || content.trim().isEmpty()) {
            return "";
        }

        String[] words = content.trim().split("\\s+");
        String firstWord = words[0];
        String lastWord = words[words.length - 1];

        messageHash = messageID.substring(0, 2) + ":"
                + messageNumber + ":"
                + firstWord.toUpperCase() + lastWord.toUpperCase();

        return messageHash;
    }

    public String SentMessage() {
        status = "Sent";
        return "Message sent successfully.";
    }

    public String printMessages() {
        return "Message ID: " + messageID
                + "\nMessage Hash: " + createMessageHash()
                + "\nSender: " + sender
                + "\nRecipient: " + recipient
                + "\nMessage: " + content
                + "\nStatus: " + status;
    }

    public String getMessageID() {
        return messageID;
    }

    public int getMessageNumber() {
        return messageNumber;
    }

    public String getSender() {
        return sender;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
