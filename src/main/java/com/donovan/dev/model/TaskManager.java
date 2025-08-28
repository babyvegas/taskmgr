package com.donovan.dev.model;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;

    public TaskManager() {
        this.tasks = new ArrayList<>();
    }

    public List<Task> getAllTasks() {
        return tasks;
    }

    public void addTask(Task task) {
        Task newTask = new Task();
        if(tasks.isEmpty()){
            newTask.setId("1");
        } else {
            int newId = Integer.parseInt(tasks.get(tasks.size() - 1).getId()) + 1;
            newTask.setId(String.valueOf(newId));
        }
        newTask.setCompleted(false);
        newTask.setCreatedAt(Timestamp.valueOf(LocalDateTime.now()));
        newTask.setDescription(task.getDescription());
        tasks.add(newTask);
    }
}
