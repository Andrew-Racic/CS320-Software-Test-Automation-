package com.grandstand.services;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class TaskServiceTest {

    // Test adding a task successfully
    @Test
    public void testAddTaskSuccess() {
        TaskService service = new TaskService();
        Task task = new Task("T001", "Task Name", "Task Description");
        service.addTask(task);
        // Verify that the task was added
        Task retrievedTask = service.getTask("T001");
        assertEquals(task, retrievedTask);
    }

    // Test adding a task with duplicate ID
    @Test
    public void testAddTaskDuplicateId() {
        TaskService service = new TaskService();
        Task task1 = new Task("T001", "Task One", "Description One");
        Task task2 = new Task("T001", "Task Two", "Description Two");
        service.addTask(task1);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(task2);
        });
        assertEquals("Task ID already exists.", exception.getMessage());
    }

    // Test adding a null task
    @Test
    public void testAddNullTask() {
        TaskService service = new TaskService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.addTask(null);
        });
        assertEquals("Task cannot be null.", exception.getMessage());
    }

    // Test deleting a task successfully
    @Test
    public void testDeleteTaskSuccess() {
        TaskService service = new TaskService();
        Task task = new Task("T001", "Task Name", "Task Description");
        service.addTask(task);
        service.deleteTask("T001");
        // Verify that the task was deleted
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.getTask("T001");
        });
        assertEquals("Task ID not found.", exception.getMessage());
    }

    // Test deleting a non-existent task
    @Test
    public void testDeleteTaskNonExistent() {
        TaskService service = new TaskService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask("NONEXISTENT");
        });
        assertEquals("Task ID not found.", exception.getMessage());
    }

    // Test deleting a task with null ID
    @Test
    public void testDeleteTaskNullId() {
        TaskService service = new TaskService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.deleteTask(null);
        });
        assertEquals("Task ID cannot be null or empty.", exception.getMessage());
    }

    // Test updating task's name successfully
    @Test
    public void testUpdateNameSuccess() {
        TaskService service = new TaskService();
        Task task = new Task("T001", "Old Name", "Description");
        service.addTask(task);
        service.updateName("T001", "New Name");
        assertEquals("New Name", task.getName());
    }

    // Test updating task's name with invalid value
    @Test
    public void testUpdateNameInvalid() {
        TaskService service = new TaskService();
        Task task = new Task("T001", "Old Name", "Description");
        service.addTask(task);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("T001", null);
        });
        assertEquals("Name cannot be null, empty, or longer than 20 characters.", exception.getMessage());
    }

    // Test updating name of a non-existent task
    @Test
    public void testUpdateNameNonExistentTask() {
        TaskService service = new TaskService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateName("NONEXISTENT", "New Name");
        });
        assertEquals("Task ID not found.", exception.getMessage());
    }

    // Test updating task's description successfully
    @Test
    public void testUpdateDescriptionSuccess() {
        TaskService service = new TaskService();
        Task task = new Task("T001", "Task Name", "Old Description");
        service.addTask(task);
        service.updateDescription("T001", "New Description");
        assertEquals("New Description", task.getDescription());
    }

    // Test updating task's description with invalid value
    @Test
    public void testUpdateDescriptionInvalid() {
        TaskService service = new TaskService();
        Task task = new Task("T001", "Task Name", "Old Description");
        service.addTask(task);
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("T001", null);
        });
        assertEquals("Description cannot be null, empty, or longer than 50 characters.", exception.getMessage());
    }

    // Test updating description of a non-existent task
    @Test
    public void testUpdateDescriptionNonExistentTask() {
        TaskService service = new TaskService();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            service.updateDescription("NONEXISTENT", "New Description");
        });
        assertEquals("Task ID not found.", exception.getMessage());
    }
}
