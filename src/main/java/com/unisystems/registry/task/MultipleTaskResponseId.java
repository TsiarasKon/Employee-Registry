package com.unisystems.registry.task;

import java.util.List;

public class MultipleTaskResponseId {
    private List<TaskResponseId> taskResponse;

    public MultipleTaskResponseId(List<TaskResponseId> taskResponse) {
        this.taskResponse = taskResponse;
    }

    public List<TaskResponseId> getTaskResponse() {
        return taskResponse;
    }

    public void setTaskResponse(List<TaskResponseId> taskResponse) {
        this.taskResponse = taskResponse;
    }
}
