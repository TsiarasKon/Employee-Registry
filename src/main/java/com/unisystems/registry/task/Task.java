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
    private String desc;
    private int estimationA; //double or float is better
    private int estimationB;
    private int estimationC;
    private Status status;

    @ElementCollection
    private List<String> updates;

    @ManyToMany
    private List<Employee> assignedEmployee;

    public Task(String title, String desc, int estimationA, int estimationB, int estimationC, Status status, List<String> updates) {
        this.title = title;
        this.desc = desc;
        this.estimationA = estimationA;
        this.estimationB = estimationB;
        this.estimationC = estimationC;
        this.status = status;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }
}
