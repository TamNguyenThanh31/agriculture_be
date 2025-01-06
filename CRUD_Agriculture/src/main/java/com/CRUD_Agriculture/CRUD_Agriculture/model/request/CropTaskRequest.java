package com.CRUD_Agriculture.CRUD_Agriculture.model.request;
import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.TaskStatus;

import java.time.LocalDate;

public class CropTaskRequest {
    private String taskName;
    private LocalDate dueDate;
    private TaskStatus status;
    private String notes;
    private Double cost;

    // Getters và Setters
    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }
}
