package com.CRUD_Agriculture.CRUD_Agriculture.entity;

import com.CRUD_Agriculture.CRUD_Agriculture.entity.enums.TaskStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "crop_tasks")
public class CropTask {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "crop_season_id", nullable = false)
    private CropSeason cropSeason; // Một mùa vụ có nhiều công việc

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @Column(name = "due_date", nullable = false)
    private LocalDate dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private TaskStatus status;

    @Column(name = "notes")
    private String notes;

    @Column(name = "cost")
    private Double cost;

    // Constructor không tham số
    public CropTask() {}

    // Constructor đầy đủ tham số
    public CropTask(Long id, CropSeason cropSeason, String taskName, LocalDate dueDate, TaskStatus status, String notes, Double cost) {
        this.id = id;
        this.cropSeason = cropSeason;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.status = status;
        this.notes = notes;
        this.cost = cost;
    }

    // Getter và Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CropSeason getCropSeason() {
        return cropSeason;
    }

    public void setCropSeason(CropSeason cropSeason) {
        this.cropSeason = cropSeason;
    }

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
