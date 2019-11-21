package com.unisystems.registry.task;

import com.unisystems.registry.employee.Employee;

import java.util.List;

public class TaskResponseId {
    private long id;
    private String title;
    private String desc;
    private String difficulty;
    private String status;
    private List<Employee> assignedEmployees;
    private List<String> updates;

    public TaskResponseId(long id, String title, String desc, String difficulty, String status, List<Employee> assignedEmployees, List<String> updates) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.difficulty = difficulty;
        this.status = status;
        this.assignedEmployees = assignedEmployees;
        this.updates = updates;
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
