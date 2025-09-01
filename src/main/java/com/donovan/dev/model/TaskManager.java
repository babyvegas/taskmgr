package com.donovan.dev.model;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.donovan.dev.constants.Constants;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class TaskManager {
    private List<Task> tasks = new ArrayList<>();
    private Gson gson = new Gson();

    public TaskManager() {
        loadTasks();
    }

    // load tasks from local file
    private void loadTasks(){
        try (FileReader fileReader = new FileReader(Constants.JSON_FILE_NAME)) {
            Type taskListType = new TypeToken<ArrayList<Task>>(){}.getType();
            tasks = gson.fromJson(fileReader, taskListType);
            if (tasks == null) tasks = new ArrayList<>();
        } catch (IOException e) {
            tasks = new ArrayList<>();
        }
    }

    // save tasks to local file
    private void saveTasks(){
        try(FileWriter fileWriter = new FileWriter(Constants.JSON_FILE_NAME)) {
            gson.toJson(tasks, fileWriter);
        } catch (IOException e ){
            System.out.println("Error saving tasks: " + e.getMessage());
        }
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
        saveTasks();
    }

    public void clearTasks() {
        tasks.clear();
    }

    public void removeTaskById(String id) {
        tasks.removeIf(task -> task.getId().equals(id));
        saveTasks();
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
        saveTasks();
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
