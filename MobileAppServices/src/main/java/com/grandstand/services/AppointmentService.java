package com.grandstand.services;

import java.util.HashMap;
import java.util.Map;

public class AppointmentService {

    private final Map<String, Appointment> appointments = new HashMap<>();

    // Add a new appointment
    public void addAppointment(Appointment appointment) {
        if (appointment == null) {
            throw new IllegalArgumentException("Appointment cannot be null.");
        }
        String appointmentId = appointment.getAppointmentId();
        if (appointmentId == null || appointmentId.isEmpty()) {
            throw new IllegalArgumentException("Appointment ID cannot be null or empty.");
        }

        if (appointments.containsKey(appointmentId)) {
            throw new IllegalArgumentException("Appointment ID already exists.");
        }
        appointments.put(appointmentId, appointment);
    }

    // Delete an appointment by appointmentId
    public void deleteAppointment(String appointmentId) {
        validateAppointmentId(appointmentId);

        if (appointments.remove(appointmentId) == null) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
    }

    // Retrieve an appointment by appointmentId
    public Appointment getAppointment(String appointmentId) {
        validateAppointmentId(appointmentId);
        Appointment appointment = appointments.get(appointmentId);

        if (appointment == null) {
            throw new IllegalArgumentException("Appointment ID not found.");
        }
        return appointment;
    }

    // Validate appointment ID is not null or empty
    private void validateAppointmentId(String appointmentId) {
        if (appointmentId == null || appointmentId.isEmpty()) {
            throw new IllegalArgumentException("Appointment ID cannot be null or empty.");
        }
    }
}
