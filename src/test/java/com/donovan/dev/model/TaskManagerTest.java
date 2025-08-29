package com.donovan.dev.model;

import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TaskManagerTest {

    @Test
    void shouldCreateTaskManager() {
        TaskManager taskManager = new TaskManager();
        assert(taskManager.getAllTasks().isEmpty());
    }


    @Test
    void shouldCreateTaskManagerAndAddTask() {
        TaskManager taskManager = new TaskManager();
        Task task = new Task("1", "Test Task", false, new java.sql.Timestamp(System.currentTimeMillis()));

        taskManager.addTask(task);

        assert(taskManager.getAllTasks().size() == 1);
        assert(taskManager.getAllTasks().get(0).getDescription().equals("Test Task"));
    }

    @Test
    void shouldCreateTaskManagerAndAddMultipleTasks() {
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1", "Test Task 1", false, new java.sql.Timestamp(System.currentTimeMillis()));
        Task task2 = new Task("2", "Test Task 2", false, new java.sql.Timestamp(System.currentTimeMillis()));

        taskManager.addTask(task1);
        taskManager.addTask(task2);

        assert(taskManager.getAllTasks().size() == 2);
        assert(taskManager.getAllTasks().get(0).getDescription().equals("Test Task 1"));
        assert(taskManager.getAllTasks().get(1).getDescription().equals("Test Task 2"));
        assert(taskManager.getAllTasks().get(1).getId().equals("2"));
    }

    @Test
    void shouldClearTasks(){
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1", "New Task", false, new java.sql.Timestamp(System.currentTimeMillis()));

        taskManager.clearTasks();

        assertEquals(0, taskManager.getAllTasks().size());
    }

    @Test
    void shouldRemoveTaskbyId(){
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1", "New Task", false, new java.sql.Timestamp(System.currentTimeMillis()));

        taskManager.removeTaskById("1");

        assertEquals(0, taskManager.getAllTasks().size());
    }

    @Test
    void shouldMarkTaskAsCompleted(){
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1", "New Task", false, new java.sql.Timestamp(System.currentTimeMillis()));

        taskManager.addTask(task1);
        taskManager.markTaskAsCompleted("1");

        List<Task> tasks = taskManager.getAllTasks();
        Task taskAfterMark = tasks.get(0);

        assertTrue(taskAfterMark.isCompleted());
    }

    @Test
    void shouldListTasks(){
        TaskManager taskManager = new TaskManager();
        Task task1 = new Task("1", "New Task", false, new java.sql.Timestamp(System.currentTimeMillis()));

        taskManager.addTask(task1);
        taskManager.listTasks();

    }

}
