package com.mycompany.quickchatpart3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class QuickChatTest {

    @BeforeEach
    public void setUp() {
        QuickChat.loadTestData();
    }

    @Test
    public void sentMessagesArrayIsCorrectlyPopulated() {
        assertEquals(2, QuickChat.sentMessages.length);
        assertEquals("Did you get the cake?", QuickChat.sentMessages[0].getContent());
        assertEquals("It is dinner time!", QuickChat.sentMessages[1].getContent());
    }

    @Test
    public void displaysLongestStoredMessage() {
        Message longest = QuickChat.findLongestStoredMessage();
        assertNotNull(longest);
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                longest.getContent());
    }

    @Test
    public void searchesForMessageID() {
        Message found = QuickChat.findByMessageID("0838884567");
        assertNotNull(found);
        assertEquals("It is dinner time!", found.getContent());
    }

    @Test
    public void searchesSentOrStoredMessagesByRecipient() {
        Message[] found = QuickChat.searchMessagesByRecipient("+27838884567");
        assertEquals(2, found.length);
        assertEquals("Where are you? You are late! I have asked you to be on time.",
                found[0].getContent());
        assertEquals("Ok, I am leaving without you.", found[1].getContent());
    }

    @Test
    public void deletesStoredMessageUsingItsHash() {
        String hash = QuickChat.storedMessages[0].getMessageHash();
        assertTrue(QuickChat.deleteStoredMessageByHash(hash));
        assertEquals(1, QuickChat.storedMessages.length);
        assertFalse(QuickChat.deleteStoredMessageByHash(hash));
    }

    @Test
    public void reportContainsSentMessageDetails() {
        String report = QuickChat.generateReport();
        assertTrue(report.contains("Did you get the cake?"));
        assertTrue(report.contains("It is dinner time!"));
        assertTrue(report.contains("Message Hash:"));
        assertTrue(report.contains("Recipient:"));
    }

    @Test
    public void messageArraysContainHashesAndIDs() {
        assertEquals(5, QuickChat.messageHashes.length);
        assertEquals(5, QuickChat.messageIDs.length);
        assertTrue(java.util.Arrays.asList(QuickChat.messageIDs).contains("0838884567"));
    }
}
