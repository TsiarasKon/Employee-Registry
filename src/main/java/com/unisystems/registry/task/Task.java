package com.unisystems.registry.task;

import com.unisystems.registry.employee.Employee;

import javax.persistence.*;
import java.util.List;

@Entity
public class Task {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String title;
    private String description;
    private int estimationA; //double or float is better
    private int estimationB;
    private int estimationC;
    private TaskStatus taskStatus;

    @ElementCollection
    private List<String> updates;

    @ManyToMany
    private List<Employee> assignedEmployee;

    public Task(String title, String description, int estimationA, int estimationB, int estimationC, TaskStatus taskStatus, List<String> updates) {
        this.title = title;
        this.description = description;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.taskStatus = taskStatus;
        this.updates = updates;
    }

    public Task() {
    }

    public List<Employee> getAssignedEmployee() {
        return assignedEmployee;
    }

    public void setAssignedEmployee(List<Employee> assignedEmployee) {
        this.assignedEmployee = assignedEmployee;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getEstimationA() {
        return estimationA;
    }

    public void setEstimationA(int estimationA) {
        this.estimationA = estimationA;
    }

    public int getEstimationB() {
        return estimationB;
    }

    public void setEstimationB(int estimationB) {
        this.estimationB = estimationB;
    }

    public int getEstimationC() {
        return estimationC;
    }

    public void setEstimationC(int estimationC) {
        this.estimationC = estimationC;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }
}
