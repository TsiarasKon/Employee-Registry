package com.unisystems.registry.task;

import java.util.List;

public class MultipleTaskResponse {
    private List<TaskResponse> taskResponses;

    public MultipleTaskResponse(List<TaskResponse> taskResponses) {
        this.taskResponses = taskResponses;
    }

    public List<TaskResponse> getTaskResponses() {
        return taskResponses;
    }

    public void setTaskResponses(List<TaskResponse> taskResponses) {
        this.taskResponses = taskResponses;
    }
}
