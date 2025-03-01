package com.grandstand.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

public class AppointmentServiceTest {

    private AppointmentService appointmentService;

    @BeforeEach
    public void setUp() {
        appointmentService = new AppointmentService();
    }

    // Utility method to generate a future date
    private Date futureDate(int daysAhead) {
        return new Date(System.currentTimeMillis() + (long) daysAhead * 24 * 60 * 60 * 1000);
    }

    // Test adding a new appointment
    @Test
    public void testAddAppointmentSuccess() {
        Appointment appointment = new Appointment("123", futureDate(1), "Discuss project");
        appointmentService.addAppointment(appointment);
        assertEquals(appointment, appointmentService.getAppointment("123"));
    }

    // Test adding a null appointment
    @Test
    public void testAddAppointmentNull() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(null);
        });
    }

    // Test adding an appointment with a duplicate ID
    @Test
    public void testAddAppointmentDuplicateId() {
        Appointment appointment1 = new Appointment("123", futureDate(1), "Discuss project");
        Appointment appointment2 = new Appointment("123", futureDate(2), "Follow-up discussion");

        appointmentService.addAppointment(appointment1);

        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.addAppointment(appointment2);
        });
    }

    // Test deleting an appointment by ID
    @Test
    public void testDeleteAppointmentSuccess() {
        Appointment appointment = new Appointment("123", futureDate(1), "Discuss project");
        appointmentService.addAppointment(appointment);

        appointmentService.deleteAppointment("123");

        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointment("123");
        });
    }

    // Test deleting an appointment with a non-existent ID
    @Test
    public void testDeleteAppointmentNonExistentId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.deleteAppointment("nonexistent");
        });
    }

    // Test retrieving an appointment by ID
    @Test
    public void testGetAppointmentSuccess() {
        Appointment appointment = new Appointment("123", futureDate(1), "Discuss project");
        appointmentService.addAppointment(appointment);

        Appointment retrieved = appointmentService.getAppointment("123");
        assertEquals(appointment, retrieved);
    }

    // Test retrieving an appointment with a non-existent ID
    @Test
    public void testGetAppointmentNonExistentId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointment("nonexistent");
        });
    }

    // Test validating a null appointment ID
    @Test
    public void testGetAppointmentNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointment(null);
        });
    }

    // Test validating an empty appointment ID
    @Test
    public void testGetAppointmentEmptyId() {
        assertThrows(IllegalArgumentException.class, () -> {
            appointmentService.getAppointment("");
        });
    }
}
