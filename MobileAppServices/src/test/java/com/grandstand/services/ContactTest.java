package com.grandstand.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactTest {

    // Utility method to generate a long string for testing
    private String generateLongString(int length) {
        return "a".repeat(Math.max(0, length));
    }

    // Test successful contact creation
    @Test
    public void testContactCreationSuccess() {
        Contact contact = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        assertEquals("123", contact.getContactId());
        assertEquals("Bob", contact.getFirstName());
        assertEquals("David", contact.getLastName());
        assertEquals("2033442509", contact.getPhone());
        assertEquals("123 Mulberry Street", contact.getAddress());
    }

    // Additional tests for contact creation with maximum allowed lengths
    @Test
    public void testContactCreationEdgeCaseLengths() {
        String maxId = generateLongString(10);
        String maxName = generateLongString(10);
        String maxAddress = generateLongString(30);

        Contact contact = new Contact(maxId, maxName, maxName, "1234567890", maxAddress);
        assertEquals(maxId, contact.getContactId());
        assertEquals(maxName, contact.getFirstName());
        assertEquals(maxName, contact.getLastName());
        assertEquals("1234567890", contact.getPhone());
        assertEquals(maxAddress, contact.getAddress());
    }

    // Test contact creation with invalid contactId
    @Test
    public void testContactCreationInvalidId() {
        // Null contactId
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact(null, "Bob", "David", "2033442509", "123 Mulberry Street");
        });

        // contactId longer than 10 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("12345678910", "Bob", "David", "2033442509", "123 Mulberry Street");
        });
    }

    // Test contact creation with invalid firstName
    @Test
    public void testContactCreationInvalidFirstName() {
        // Null firstName
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", null, "David", "2033442509", "123 Mulberry Street");
        });

        // firstName longer than 10 characters
        String longFirstName = generateLongString(11);
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", longFirstName, "David", "2033442509", "123 Mulberry Street");
        });

        // Empty firstName
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "", "David", "2033442509", "123 Mulberry Street");
        });

        // firstName with only whitespace
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "   ", "David", "2033442509", "123 Mulberry Street");
        });
    }

    // Test contact creation with invalid lastName
    @Test
    public void testContactCreationInvalidLastName() {
        // Null lastName
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", null, "2033442509", "123 Mulberry Street");
        });

        // lastName longer than 10 characters
        String longLastName = generateLongString(11);
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", longLastName, "2033442509", "123 Mulberry Street");
        });

        // Empty lastName
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "", "2033442509", "123 Mulberry Street");
        });
    }

    // Test contact creation with invalid phone
    @Test
    public void testContactCreationInvalidPhone() {
        // Null phone
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", null, "123 Mulberry Street");
        });

        // Phone number with less than 10 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", "033442509", "123 Mulberry Street");
        });

        // Phone number with more than 10 digits
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", "20334425090", "123 Mulberry Street");
        });

        // Phone number with non-digit characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", "20a3442509", "123 Mulberry Street");
        });
    }

    // Test contact creation with invalid address
    @Test
    public void testContactCreationInvalidAddress() {
        // Null address
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", "2033442509", null);
        });

        // Address longer than 30 characters
        String longAddress = generateLongString(31);
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", "2033442509", longAddress);
        });

        // Empty address
        assertThrows(IllegalArgumentException.class, () -> {
            new Contact("123", "Bob", "David", "2033442509", "");
        });
    }

    // Test updating contact fields successfully
    @Test
    public void testContactUpdateMethodsSuccess() {
        Contact contact = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");

        // Update firstName
        contact.setFirstName("Carl");
        assertEquals("Carl", contact.getFirstName());

        // Update lastName
        contact.setLastName("Barb");
        assertEquals("Barb", contact.getLastName());

        // Update phone
        contact.setPhone("1234567890");
        assertEquals("1234567890", contact.getPhone());

        // Update address
        contact.setAddress("321 Bradley Street");
        assertEquals("321 Bradley Street", contact.getAddress());
    }

    // Test updating contact fields with invalid values
    @Test
    public void testContactUpdateMethodsInvalid() {
        Contact contact = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");

        // Invalid firstName updates
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("SuperLongFirstName");
        });

        // Empty firstName
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("");
        });

        // Whitespace firstName
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("   ");
        });

        // Invalid lastName updates
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("SuperLongLastName");
        });

        // Empty lastName
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("");
        });

        // Invalid phone updates
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone(null);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("123456789"); // Less than 10 digits
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("12345678901"); // More than 10 digits
        });

        assertThrows(IllegalArgumentException.class, () -> {
            contact.setPhone("12345abcde"); // Non-digit characters
        });

        // Invalid address updates
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(null);
        });

        String longAddress = generateLongString(31);
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress(longAddress);
        });

        // Empty address
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setAddress("");
        });
    }

    // Additional test to boost coverage: Test setting fields with valid edge case values
    @Test
    public void testContactSettersEdgeCases() {
        Contact contact = new Contact("123", "Bob", "David", "1234567890", "123 Main St");

        // Set firstName to exactly 10 characters
        String tenCharName = generateLongString(10);
        contact.setFirstName(tenCharName);
        assertEquals(tenCharName, contact.getFirstName());

        // Set lastName to exactly 10 characters
        contact.setLastName(tenCharName);
        assertEquals(tenCharName, contact.getLastName());

        // Set phone to exactly 10 digits
        contact.setPhone("0987654321");
        assertEquals("0987654321", contact.getPhone());

        // Set address to exactly 30 characters
        String thirtyCharAddress = generateLongString(30);
        contact.setAddress(thirtyCharAddress);
        assertEquals(thirtyCharAddress, contact.getAddress());
    }

    // Tests for empty and whitespace strings in firstName and lastName during updates
    @Test
    public void testContactUpdateFirstNameEmptyWhitespace() {
        Contact contact = new Contact("123", "Bob", "David", "1234567890", "123 Main St");

        // Empty firstName
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("");
        });

        // Whitespace firstName
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setFirstName("   ");
        });
    }

    @Test
    public void testContactUpdateLastNameEmptyWhitespace() {
        Contact contact = new Contact("123", "Bob", "David", "1234567890", "123 Main St");

        // Empty lastName
        assertThrows(IllegalArgumentException.class, () -> {
            contact.setLastName("");
        });

    }

    // Additional tests for the equals method
    @Test
    public void testContactEqualsNull() {
        Contact contact = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        assertNotEquals(contact, null);
    }

    @Test
    public void testContactEqualsDifferentClass() {
        Contact contact = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        String notAContact = "Not a Contact";
        assertNotEquals(contact, notAContact);
    }

    @Test
    public void testContactEqualsSelf() {
        Contact contact = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        assertEquals(contact, contact);
    }

    @Test
    public void testContactEqualsDifferentFirstName() {
        Contact contact1 = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        Contact contact2 = new Contact("123", "Rob", "David", "2033442509", "123 Mulberry Street");
        assertNotEquals(contact1, contact2);
    }

    @Test
    public void testContactEqualsDifferentLastName() {
        Contact contact1 = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        Contact contact2 = new Contact("123", "Bob", "Smith", "2033442509", "123 Mulberry Street");
        assertNotEquals(contact1, contact2);
    }

    @Test
    public void testContactEqualsDifferentPhone() {
        Contact contact1 = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        Contact contact2 = new Contact("123", "Bob", "David", "1234567890", "123 Mulberry Street");
        assertNotEquals(contact1, contact2);
    }

    @Test
    public void testContactEqualsDifferentAddress() {
        Contact contact1 = new Contact("123", "Bob", "David", "2033442509", "123 Mulberry Street");
        Contact contact2 = new Contact("123", "Bob", "David", "2033442509", "321 Elm Street");
        assertNotEquals(contact1, contact2);
    }

    // Additional tests for the hashCode method with different data
    @Test
    public void testContactHashCodeDifferentData() {
        Contact contact1 = new Contact("123", "Bob", "David", "1234567890", "Address One");
        Contact contact2 = new Contact("123", "Bob", "David", "1234567890", "Address Two");

        assertNotEquals(contact1.hashCode(), contact2.hashCode());
    }
}
