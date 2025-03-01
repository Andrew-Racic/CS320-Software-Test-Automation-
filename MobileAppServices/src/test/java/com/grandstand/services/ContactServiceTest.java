package com.grandstand.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ContactServiceTest {

    // Test adding a contact successfully
    @Test
    public void testAddContactSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        // Verify that the contact was added
        Contact retrievedContact = service.getContact("ABC123");
        assertEquals(contact, retrievedContact);
    }

    // Test adding a contact with duplicate ID
    @Test
    public void testAddContactDuplicateId() {
        ContactService service = new ContactService();
        Contact contact1 = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        Contact contact2 = new Contact("ABC123", "Jane", "Smith", "0987654321", "456 Elm St");
        service.addContact(contact1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(contact2);
        });
        assertEquals("Contact ID already exists.", exception.getMessage());
    }

    // Test adding a null contact
    @Test
    public void testAddNullContact() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addContact(null);
        });
        assertEquals("Contact cannot be null.", exception.getMessage());
    }

    // Test deleting a contact successfully
    @Test
    public void testDeleteContactSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.deleteContact("ABC123");
        // Verify that the contact was deleted
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getContact("ABC123");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    // Test deleting a non-existent contact
    @Test
    public void testDeleteContactNonExistent() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact("NONEXISTENT");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    // Test deleting a contact with null ID
    @Test
    public void testDeleteContactNullId() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteContact(null);
        });
        assertEquals("Contact ID cannot be null or empty.", exception.getMessage());
    }

    // Test updating contact's first name successfully
    @Test
    public void testUpdateFirstNameSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateFirstName("ABC123", "Jane");
        assertEquals("Jane", contact.getFirstName());
    }

    // Test updating contact's first name with invalid value
    @Test
    public void testUpdateFirstNameInvalid() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("ABC123", null);
        });
        assertEquals("First name cannot be null or longer than 10 characters.", exception.getMessage());
    }

    // Test updating first name of a non-existent contact
    @Test
    public void testUpdateFirstNameNonExistentContact() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateFirstName("NONEXISTENT", "Jane");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    // Test updating contact's last name successfully
    @Test
    public void testUpdateLastNameSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        service.updateLastName("ABC123", "Smith");
        assertEquals("Smith", contact.getLastName());
    }

    // Test updating contact's last name with invalid value
    @Test
    public void testUpdateLastNameInvalid() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("ABC123", null);
        });
        assertEquals("Last name cannot be null or longer than 10 characters.", exception.getMessage());
    }

    // Test updating last name of a non-existent contact
    @Test
    public void testUpdateLastNameNonExistentContact() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateLastName("NONEXISTENT", "Smith");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    // Test updating contact's phone number successfully
    @Test
    public void testUpdatePhoneSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1111111111", "123 Main St");
        service.addContact(contact);
        service.updatePhone("ABC123", "2222222222");
        assertEquals("2222222222", contact.getPhone());
    }

    // Test updating contact's phone number with invalid value
    @Test
    public void testUpdatePhoneInvalid() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1111111111", "123 Main St");
        service.addContact(contact);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("ABC123", "123"); // Invalid phone number
        });
        assertEquals("Phone number must be exactly 10 digits.", exception.getMessage());
    }

    // Test updating phone number of a non-existent contact
    @Test
    public void testUpdatePhoneNonExistentContact() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updatePhone("NONEXISTENT", "2222222222");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    // Test updating contact's address successfully
    @Test
    public void testUpdateAddressSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1111111111", "123 Main St");
        service.addContact(contact);
        service.updateAddress("ABC123", "456 Elm St");
        assertEquals("456 Elm St", contact.getAddress());
    }

    // Test updating contact's address with invalid value
    @Test
    public void testUpdateAddressInvalid() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1111111111", "123 Main St");
        service.addContact(contact);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("ABC123", null);
        });
        assertEquals("Address cannot be null or longer than 30 characters.", exception.getMessage());
    }

    // Test updating address of a non-existent contact
    @Test
    public void testUpdateAddressNonExistentContact() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateAddress("NONEXISTENT", "456 Elm St");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }

    // Test retrieving a contact successfully
    @Test
    public void testGetContactSuccess() {
        ContactService service = new ContactService();
        Contact contact = new Contact("ABC123", "John", "Doe", "1234567890", "123 Main St");
        service.addContact(contact);
        Contact retrievedContact = service.getContact("ABC123");
        assertEquals(contact, retrievedContact);
    }

    // Test retrieving a non-existent contact
    @Test
    public void testGetContactNonExistent() {
        ContactService service = new ContactService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getContact("NONEXISTENT");
        });
        assertEquals("Contact ID not found.", exception.getMessage());
    }
}
