package com.grandstand.services;

import java.util.Objects;

public class Contact {
    private String contactId;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    private static final int ID_MAX_LENGTH = 10;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int ADDRESS_MAX_LENGTH = 30;

    public Contact(String contactId, String firstName, String lastName, String phone, String address) {
        validateContactId(contactId);
        validateFirstName(firstName);
        validateLastName(lastName);
        validatePhone(phone);
        validateAddress(address);

        this.contactId = contactId;
        this.firstName = firstName.trim();
        this.lastName = lastName.trim();
        this.phone = phone;
        this.address = address.trim();
    }

    // Validation methods
    private void validateContactId(String contactId) {
        if (contactId == null || contactId.length() > ID_MAX_LENGTH) {
            throw new IllegalArgumentException("Contact ID cannot be null or longer than 10 characters.");
        }
    }

    private void validateFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty() || firstName.trim().length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("First name cannot be null or longer than 10 characters.");
        }
    }

    private void validateLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty() || lastName.trim().length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("Last name cannot be null or longer than 10 characters.");
        }
    }

    private void validatePhone(String phone) {
        if (phone == null || !phone.matches("\\d{10}")) {
            throw new IllegalArgumentException("Phone number must be exactly 10 digits.");
        }
    }

    private void validateAddress(String address) {
        if (address == null || address.trim().isEmpty() || address.trim().length() > ADDRESS_MAX_LENGTH) {
            throw new IllegalArgumentException("Address cannot be null or longer than 30 characters.");
        }
    }

    // Getter and Setter methods
    public String getContactId() {
        return contactId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        validateFirstName(firstName);
        this.firstName = firstName.trim();
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        validateLastName(lastName);
        this.lastName = lastName.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        validatePhone(phone);
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        validateAddress(address);
        this.address = address.trim();
    }

    // equals and hashCode methods
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Contact contact = (Contact) o;

        return Objects.equals(contactId, contact.contactId) &&
               Objects.equals(firstName, contact.firstName) &&
               Objects.equals(lastName, contact.lastName) &&
               Objects.equals(phone, contact.phone) &&
               Objects.equals(address, contact.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(contactId, firstName, lastName, phone, address);
    }
}
