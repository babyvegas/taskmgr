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

    public void clearTasks() {
        tasks.clear();
    }

    public void removeTaskById(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
    }

    public void markTaskAsCompleted(String id) {
        boolean found = false;
        for (Task task : tasks) {
            if (task.getId().equals(id)) {
                task.setCompleted(true);
                found = true;
                break;
            }
        }
        if (!found){
            System.out.printf("Couldn't find a task with id %s\n", id);
        }
    }

    public void listTasks(){
        if(tasks.isEmpty()){
            System.out.println("You don't have any task pending, congrats!");
        }
        for(Task task : tasks){
            String status = task.isCompleted() ? "[x]" : "[]";
            System.out.printf("%s (%s) %s - created %s%n",
                    status, task.getId(), task.getDescription(), task.getCreatedAt());
        }
    }
}
