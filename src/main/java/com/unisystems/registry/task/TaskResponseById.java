package com.unisystems.registry.task;

import com.unisystems.registry.employee.Employee;

public class TaskResponseById {

    private long id;
    private String title;
    private String desc;
    private String difficulty;
    private Status status;
    private Employee employee;

    public TaskResponseById(long id, String title, String desc, String difficulty, Status status, Employee employee) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.difficulty = difficulty;
        this.status = status;
        this.employee = employee;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
