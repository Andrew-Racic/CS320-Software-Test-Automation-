package com.grandstand.services;

import java.util.HashMap;
import java.util.Map;

public class ContactService {

    private final Map<String, Contact> contacts = new HashMap<>();

    // Add a new contact
    public void addContact(Contact contact) {
        if (contact == null) {
            throw new IllegalArgumentException("Contact cannot be null.");
        }
        String contactId = contact.getContactId();
        if (contactId == null || contactId.length() == 0) {
            throw new IllegalArgumentException("Contact ID cannot be null or empty.");
        }
        if (contacts.containsKey(contactId)) {
            throw new IllegalArgumentException("Contact ID already exists.");
        }
        contacts.put(contactId, contact);
    }

    // Delete a contact by contactId
    public void deleteContact(String contactId) {
        if (contactId == null || contactId.length() == 0) {
            throw new IllegalArgumentException("Contact ID cannot be null or empty.");
        }
        if (contacts.remove(contactId) == null) {
            throw new IllegalArgumentException("Contact ID not found.");
        }
    }

    // Update contact's first name
    public void updateFirstName(String contactId, String firstName) {
        Contact contact = getContact(contactId);
        contact.setFirstName(firstName);
    }

    // Update contact's last name
    public void updateLastName(String contactId, String lastName) {
        Contact contact = getContact(contactId);
        contact.setLastName(lastName);
    }

    // Update contact's phone number
    public void updatePhone(String contactId, String phone) {
        Contact contact = getContact(contactId);
        contact.setPhone(phone);
    }

    // Update contact's address
    public void updateAddress(String contactId, String address) {
        Contact contact = getContact(contactId);
        contact.setAddress(address);
    }

    // Helper method to get a contact by ID
    Contact getContact(String contactId) {
        if (contactId == null || contactId.length() == 0) {
            throw new IllegalArgumentException("Contact ID cannot be null or empty.");
        }
        Contact contact = contacts.get(contactId);
        if (contact == null) {
            throw new IllegalArgumentException("Contact ID not found.");
        }
        return contact;
    }
}
