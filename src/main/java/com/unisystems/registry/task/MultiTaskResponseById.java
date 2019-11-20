package com.unisystems.registry.task;

import java.util.List;

public class MultiTaskResponseById {
    private List<TaskResponseById> taskResponseByIdList;

    public List<TaskResponseById> getTaskResponseByIdList() {
        return taskResponseByIdList;
    }

    public void setTaskResponseByIdList(List<TaskResponseById> taskResponseByIdList) {
        this.taskResponseByIdList = taskResponseByIdList;
    }

    public MultiTaskResponseById(List<TaskResponseById> taskResponseByIdList) {
        this.taskResponseByIdList = taskResponseByIdList;
    }



}
