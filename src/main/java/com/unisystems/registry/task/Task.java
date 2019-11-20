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
    private int estimationa; //double or float is better
    private int estimationb;
    private int estimationc;
    private Status status;

    @ElementCollection
    private List<String> updates;

    @ManyToMany
    private List<Employee> assignedEmployees;

    public Task(String title, String desc, int estimationa, int estimationb, int estimationc, Status status, List<String> updates) {
        this.title = title;
        this.desc = desc;
        this.estimationa = estimationa;
        this.estimationb = estimationb;
        this.estimationc = estimationc;
        this.status = status;
        this.updates = updates;
    }

    public Task() {
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
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

    public int getEstimationa() {
        return estimationa;
    }

    public void setEstimationa(int estimationa) {
        this.estimationa = estimationa;
    }

    public int getEstimationb() {
        return estimationb;
    }

    public void setEstimationb(int estimationb) {
        this.estimationb = estimationb;
    }

    public int getEstimationc() {
        return estimationc;
    }

    public void setEstimationc(int estimationc) {
        this.estimationc = estimationc;
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
