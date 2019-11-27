package com.unisystems.registry.task;

import java.util.List;

public class MultipleTaskResponse {
    private List<TaskResponse> taskResponse;

    public MultipleTaskResponse(List<TaskResponse> taskResponse) {
        this.taskResponse = taskResponse;
    }

    public List<TaskResponse> getTaskResponse() {
        return taskResponse;
    }

    public void setTaskResponse(List<TaskResponse> taskResponse) {
        this.taskResponse = taskResponse;
    }
}
