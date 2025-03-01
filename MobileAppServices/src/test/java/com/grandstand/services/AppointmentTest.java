package com.grandstand.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppointmentTest {

    private Date futureDate() {
        // Utility method to obtain a date in the future
        return new Date(System.currentTimeMillis() + 86400000); // 1 day in the future
    }

    private Date pastDate() {
        // Utility method to obtain a date in the past
        return new Date(System.currentTimeMillis() - 86400000); // 1 day in the past
    }

    // Test successful appointment creation
    @Test
    public void testAppointmentCreationSuccess() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        assertEquals("123", appointment.getAppointmentId());
        assertNotNull(appointment.getAppointmentDate());
        assertEquals("Discuss project", appointment.getDescription());
    }

    // Test appointment creation with null ID
    @Test
    public void testAppointmentCreationNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(null, futureDate(), "Discuss project");
        });
    }

    // Test appointment creation with long ID
    @Test
    public void testAppointmentCreationLongId() {
        String longId = "12345678901"; // 11 characters
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment(longId, futureDate(), "Discuss project");
        });
    }

    // Test appointment creation with null date
    @Test
    public void testAppointmentCreationNullDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", null, "Discuss project");
        });
    }

    // Test appointment creation with past date
    @Test
    public void testAppointmentCreationPastDate() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", pastDate(), "Discuss project");
        });
    }

    // Test appointment creation with null description
    @Test
    public void testAppointmentCreationNullDescription() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", futureDate(), null);
        });
    }

    // Test appointment creation with long description
    @Test
    public void testAppointmentCreationLongDescription() {
        String longDescription = "This is a very long description that exceeds fifty characters in length.";
        assertThrows(IllegalArgumentException.class, () -> {
            new Appointment("123", futureDate(), longDescription);
        });
    }

    // Test setting a new valid appointment date
    @Test
    public void testSetAppointmentDateSuccess() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        Date newDate = new Date(System.currentTimeMillis() + 172800000); // 2 days in the future
        appointment.setAppointmentDate(newDate);
        assertEquals(newDate, appointment.getAppointmentDate());
    }

    // Test setting a null appointment date
    @Test
    public void testSetAppointmentDateNull() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(null);
        });
    }

    // Test setting a past appointment date
    @Test
    public void testSetAppointmentDatePast() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setAppointmentDate(pastDate());
        });
    }

    // Test setting a new valid description
    @Test
    public void testSetDescriptionSuccess() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        appointment.setDescription("New description");
        assertEquals("New description", appointment.getDescription());
    }

    // Test setting a null description
    @Test
    public void testSetDescriptionNull() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(null);
        });
    }

    // Test setting a long description
    @Test
    public void testSetDescriptionLong() {
        Appointment appointment = new Appointment("123", futureDate(), "Discuss project");
        String longDescription = "This is a very long description that exceeds fifty characters in length.";
        assertThrows(IllegalArgumentException.class, () -> {
            appointment.setDescription(longDescription);
        });
    }

    // Test equals method
    @Test
    public void testEqualsMethod() {
        Appointment appointment1 = new Appointment("123", futureDate(), "Discuss project");
        Appointment appointment2 = new Appointment("123", appointment1.getAppointmentDate(), "Discuss project");
        Appointment appointment3 = new Appointment("124", futureDate(), "Discuss project");

        assertEquals(appointment1, appointment2);
        assertNotEquals(appointment1, appointment3);
    }

    // Test hashCode method
    @Test
    public void testHashCodeMethod() {
        Appointment appointment1 = new Appointment("123", futureDate(), "Discuss project");
        Appointment appointment2 = new Appointment("123", appointment1.getAppointmentDate(), "Discuss project");

        assertEquals(appointment1.hashCode(), appointment2.hashCode());
    }
}
