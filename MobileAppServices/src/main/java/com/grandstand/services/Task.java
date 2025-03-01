package com.grandstand.services;

public class Task {

    private final String taskId; 
    private String name;
    private String description;

    // Constants for validation
    private static final int ID_MAX_LENGTH = 10;
    private static final int NAME_MAX_LENGTH = 20;
    private static final int DESCRIPTION_MAX_LENGTH = 50;

    // Constructor
    public Task(String taskId, String name, String description) {
        if (taskId == null || taskId.length() == 0 || taskId.length() > ID_MAX_LENGTH) {
            throw new IllegalArgumentException("Task ID cannot be null, empty, or longer than 10 characters.");
        }
        this.taskId = taskId;

        setName(name);
        setDescription(description);
    }

    // Getters
    public String getTaskId() {
        return taskId;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    // Setters for fields
    public void setName(String name) {
        if (name == null || name.length() == 0 || name.length() > NAME_MAX_LENGTH) {
            throw new IllegalArgumentException("Name cannot be null, empty, or longer than 20 characters.");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        if (description == null || description.length() == 0 || description.length() > DESCRIPTION_MAX_LENGTH) {
            throw new IllegalArgumentException("Description cannot be null, empty, or longer than 50 characters.");
        }
        this.description = description;
    }
}
