package com.donovan.dev;

import com.donovan.dev.model.Task;
import com.donovan.dev.model.TaskManager;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();
        System.out.println("Welcome to the task manager.");
        Boolean exit = false;
        while(!exit){
            System.out.println("1. Add Task\n");
            System.out.println("2. List Tasks\n");
            System.out.println("3. Mark Task as Complete\n");
            System.out.println("4. Exit\n");
            Scanner scanner = new Scanner(System.in);
            String decision = scanner.nextLine();
            switch (decision){
                case "1":
                    Task task = new Task();
                    System.out.println("Please enter the description of the task\n");
                    String description = scanner.nextLine();
                    task.setDescription(description);
                    taskManager.addTask(task);
                    exit = false;
                    break;
                case "2":
                    taskManager.listTasks();
                    exit = false;
                    break;
                case "3":
                    System.out.println("Which task you want to complete?");
                    Scanner scanner1 = new Scanner(System.in);
                    String id = scanner1.nextLine();
                    taskManager.markTaskAsCompleted(id);
                    exit = false;
                    break;
                case "4":
                    exit = true;
                default:
                    break;
            }
        }
    }
}