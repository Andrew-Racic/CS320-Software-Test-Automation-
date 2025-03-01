package com.grandstand.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Appointment {
    private String appointmentId;
    private Date appointmentDate;
    private String description;

    private static final int ID_LENGTH = 10;
    private static final int DESCRIPTION_LENGTH = 50;

    public Appointment(String appointmentId, Date appointmentDate, String description) {
        validateAppointmentId(appointmentId);
        validateAppointmentDate(appointmentDate);
        validateDescription(description);

        this.appointmentId = appointmentId;
        this.appointmentDate = new Date(appointmentDate.getTime()); // Defensive copy
        this.description = description;
    }

    private void validateAppointmentId(String appointmentId) {
        if (appointmentId == null || appointmentId.length() > ID_LENGTH) {
            throw new IllegalArgumentException("Appointment ID cannot be null or longer than " + ID_LENGTH + " characters.");
        }
    }

    private void validateAppointmentDate(Date appointmentDate) {
        if (appointmentDate == null || appointmentDate.before(new Date())) {
            throw new IllegalArgumentException("Appointment date cannot be null or in the past.");
        }
    }

    private void validateDescription(String description) {
        if (description == null || description.length() > DESCRIPTION_LENGTH) {
            throw new IllegalArgumentException("Description cannot be null or longer than " + DESCRIPTION_LENGTH + " characters.");
        }
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public Date getAppointmentDate() {
        return new Date(appointmentDate.getTime()); // Defensive copy
    }

    public void setAppointmentDate(Date appointmentDate) {
        validateAppointmentDate(appointmentDate);
        this.appointmentDate = new Date(appointmentDate.getTime()); // Defensive copy
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        validateDescription(description);
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Appointment that = (Appointment) o;

        return Objects.equals(appointmentId, that.appointmentId) &&
               Objects.equals(appointmentDate, that.appointmentDate) &&
               Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(appointmentId, appointmentDate, description);
    }
}
