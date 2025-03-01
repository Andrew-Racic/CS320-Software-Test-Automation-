package com.grandstand.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskTest {

    // Test successful task creation
    @Test
    public void testTaskCreationSuccess() {
        Task task = new Task("T001", "Task Name", "Task Description");
        assertEquals("T001", task.getTaskId());
        assertEquals("Task Name", task.getName());
        assertEquals("Task Description", task.getDescription());
    }

    // Test task creation with null ID
    @Test
    public void testTaskCreationNullId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(null, "Task Name", "Task Description");
        });
        assertEquals("Task ID cannot be null, empty, or longer than 10 characters.", exception.getMessage());
    }

    // Test task creation with empty ID
    @Test
    public void testTaskCreationEmptyId() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task("", "Task Name", "Task Description");
        });
        assertEquals("Task ID cannot be null, empty, or longer than 10 characters.", exception.getMessage());
    }

    // Test task creation with ID longer than 10 characters
    @Test
    public void testTaskCreationLongId() {
        String longId = "12345678901"; // 11 characters
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Task(longId, "Task Name", "Task Description");
        });
        assertEquals("Task ID cannot be null, empty, or longer than 10 characters.", exception.getMessage());
    }

    // Test setting invalid name
    @Test
    public void testSetNameInvalid() {
        Task task = new Task("T001", "Task Name", "Task Description");

        // Setting name to null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setName(null);
        });
        assertEquals("Name cannot be null, empty, or longer than 20 characters.", exception.getMessage());

        // Setting name to empty string
        exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setName("");
        });
        assertEquals("Name cannot be null, empty, or longer than 20 characters.", exception.getMessage());

        // Setting name longer than 20 characters
        String longName = generateLongString(21);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setName(longName);
        });
        assertEquals("Name cannot be null, empty, or longer than 20 characters.", exception.getMessage());
    }

    // Test updating name successfully
    @Test
    public void testSetNameSuccess() {
        Task task = new Task("T001", "Old Name", "Task Description");
        task.setName("New Name");
        assertEquals("New Name", task.getName());
    }

    // Test setting invalid description
    @Test
    public void testSetDescriptionInvalid() {
        Task task = new Task("T001", "Task Name", "Task Description");

        // Setting description to null
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(null);
        });
        assertEquals("Description cannot be null, empty, or longer than 50 characters.", exception.getMessage());

        // Setting description to empty string
        exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription("");
        });
        assertEquals("Description cannot be null, empty, or longer than 50 characters.", exception.getMessage());

        // Setting description longer than 50 characters
        String longDescription = generateLongString(51);
        exception = assertThrows(IllegalArgumentException.class, () -> {
            task.setDescription(longDescription);
        });
        assertEquals("Description cannot be null, empty, or longer than 50 characters.", exception.getMessage());
    }

    // Test updating description successfully
    @Test
    public void testSetDescriptionSuccess() {
        Task task = new Task("T001", "Task Name", "Old Description");
        task.setDescription("New Description");
        assertEquals("New Description", task.getDescription());
    }

    // Helper method to generate a string of a specific length
    private String generateLongString(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            sb.append('A');
        }
        return sb.toString();
    }
}
