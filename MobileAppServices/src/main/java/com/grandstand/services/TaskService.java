package com.grandstand.services;

import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks = new HashMap<>();

    // Add a new task
    public void addTask(Task task) {
        validateTask(task);
        String taskId = task.getTaskId();
        validateTaskId(taskId);
        
        if (tasks.containsKey(taskId)) {
            throw new IllegalArgumentException("Task ID already exists.");
        }
        tasks.put(taskId, task);
    }

    // Delete a task by taskId
    public void deleteTask(String taskId) {
        validateTaskId(taskId);
        
        if (tasks.remove(taskId) == null) {
            throw new IllegalArgumentException("Task ID not found.");
        }
    }

    // Update task's name
    public void updateName(String taskId, String name) {
        Task task = getTask(taskId);
        task.setName(name);
    }

    // Update task's description
    public void updateDescription(String taskId, String description) {
        Task task = getTask(taskId);
        task.setDescription(description);
    }

    // Helper method to get a task by ID
    public Task getTask(String taskId) {
        validateTaskId(taskId);
        Task task = tasks.get(taskId);
        
        if (task == null) {
            throw new IllegalArgumentException("Task ID not found.");
        }
        return task;
    }

    // Validate task is not null
    private void validateTask(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Task cannot be null.");
        }
    }

    // Validate task ID is not null or empty
    private void validateTaskId(String taskId) {
        if (taskId == null || taskId.isEmpty()) {
            throw new IllegalArgumentException("Task ID cannot be null or empty.");
        }
    }
}
