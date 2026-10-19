/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject1;

/**
 *
 * @author Student
 */
public class Test {
    
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class MessageTest {
// Test username validation
@Test
public void testCheckUserName() {
assert True( Mavenproject1.CheckUserName("li_1"));
assert False( Mavenproject1.CheckUserName("li12345"));
}
// Test password validation
@Test
public void testCheckPassword() {
assert True( Mavenproject1.CheckPassword("Password1!"));
assert False( Mavenproject1.CheckPassword("password"));
}
// Test cellphone validation
@Test
public void testCheckCellPhone() {
assert True( Mavenproject1.CheckCellPhone("+27821234567"));
assert False( Mavenproject1.CheckCellPhone("0821234567"));
}
// Test successful login
@Test
public void testLoginSuccess() {
assert True( Mavenproject1.LoginUser(
"li_1", "Password1!", "li_1", "Password1!"
));
}
// Test unsuccessful login
@Test
public void testLoginFailure() {
assert False( Mavenproject1.LoginUser(
"li_1", "WrongPass1!", "li_1", "Password1!"
));
}
// Test message ID
@Test
public void testMessageID() {
Message message = new Message(
1, "+27821234567", "Hello there"
);
assertTrue(message.checkMessageID());
assertEquals(10, message.getMessageID().length());
}
// Test valid recipient cellphone number
@Test
public void testValidRecipient() {
Message message = new Message(
1, "+27821234567", "Hello there"
);
assertTrue(message.checkRecipientCell());
}
// Test invalid recipient cellphone number
@Test
public void testInvalidRecipient() {
Message message = new Message(
1, "0821234567", "Hello there"
);
assertFalse(message.checkRecipientCell());
}
// Test message hash
@Test
public void testMessageHash() {
Message message = new Message(
1, "+27821234567", "Hello World"
);
String hash = message.createMessageHash();
assert NotNull(hash);
assertF alse(hash.isEmpty());
}
// Test message details
@Test
public void testSentMessage() {
Messages message = new Message(
1, "+27821234567", "Hello World"
);
String details = message.printMessages();
assert True(details.contains("Message ID:"));
assert True(details.contains("Recipient:"));
assert True(details.contains("Hello World"));
}
}
    
}
