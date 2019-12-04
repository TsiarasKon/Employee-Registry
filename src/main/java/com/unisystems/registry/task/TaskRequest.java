package com.unisystems.registry.task;

import com.unisystems.registry.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

public class TaskRequest {
    private String title;
    private String desc;
    private int estimationA;
    private int estimationB;
    private int estimationC;
    private String taskStatus;
    private List<String> updates;
    private List<Long> assignedEmployeeIds;

    private TaskStatus taskStatusValid;

    public TaskRequest() {
        taskStatusValid = TaskStatus.NEW;
        updates = new ArrayList<>();
        assignedEmployeeIds = new ArrayList<>();
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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    public List<String> getUpdates() {
        return updates;
    }

    public void setUpdates(List<String> updates) {
        this.updates = updates;
    }

    public List<Long> getAssignedEmployeeIds() {
        return assignedEmployeeIds;
    }

    public void setAssignedEmployeeIds(List<Long> assignedEmployeeIds) {
        this.assignedEmployeeIds = assignedEmployeeIds;
    }

    public TaskStatus getTaskStatusValid() {
        return taskStatusValid;
    }

    public void setTaskStatusValid(TaskStatus taskStatusValid) {
        this.taskStatusValid = taskStatusValid;
    }

    public ResponseEntity<Object> validateRequest() {
        if (title == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide title"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (desc == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide desc"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (estimationA <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide a positive integer estimationA"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (estimationB <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide a positive integer estimationB"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (estimationC <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide a positive integer estimationC"),
                    HttpStatus.BAD_REQUEST
            );
        }

        if (taskStatus != null) {
            try {
                this.taskStatusValid = TaskStatus.valueOf(taskStatus);
            } catch (IllegalArgumentException | NullPointerException  e) {
                return new ResponseEntity<>(
                        new GenericError(2, "Invalid request body", "Please provide a valid taskStatus"),
                        HttpStatus.BAD_REQUEST
                );
            }
        }

        return null;
    }
}
