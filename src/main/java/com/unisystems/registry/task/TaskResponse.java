package com.unisystems.registry.task;

import com.unisystems.registry.employee.Employee;

import java.util.List;

public class TaskResponse {
    private long id;

    private String title;
    private String desc;
    private String dificulty;
    private Status status;




    //private Employee assignedEmployees;

    public TaskResponse(long id, String title, String desc, String dificulty, Status status) {
        this.id = id;
        this.title = title;
        this.desc = desc;
        this.dificulty = dificulty;
        this.status = status;
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

    public String getDificulty() {
        return dificulty;
    }

    public void setDificulty(String dificulty) {
        this.dificulty = dificulty;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
