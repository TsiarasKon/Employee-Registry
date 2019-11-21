package com.unisystems.registry.task;

import java.util.List;

public class MultiTaskResponseById {
    private List<TaskResponseById> taskResponseById;

    public List<TaskResponseById> getTaskResponseById() {
        return taskResponseById;
    }

    public void setTaskResponseById(List<TaskResponseById> taskResponseById) {
        this.taskResponseById = taskResponseById;
    }

    public MultiTaskResponseById(List<TaskResponseById> taskResponseById) {
        this.taskResponseById = taskResponseById;
    }
}
