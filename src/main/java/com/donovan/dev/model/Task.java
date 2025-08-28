package com.donovan.dev.model;

import java.sql.Timestamp;

public class Task {

    private String id;
    private String description;
    private boolean isCompleted;
    private Timestamp createdAt;

    public Task(){
    }

    public Task(String id, String description, boolean isCompleted, Timestamp createdAt) {
        this.id = id;
        this.description = description;
        this.isCompleted = isCompleted;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
