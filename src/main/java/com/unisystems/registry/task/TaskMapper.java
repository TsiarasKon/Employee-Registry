package com.unisystems.registry.task;


import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskMapper {

    public TaskResponse mapTask(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                getStatus(task)
        );
    }

    public TaskResponseId mapTaskId(Task task){
        return new TaskResponseId(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                getStatus(task),
                task.getAssignedEmployee(),
                task.getUpdates()
        );
    }

    public MultipleTaskResponseId mapTaskList(List<Task> tasks) {
        List<TaskResponseId> taskResponse= new ArrayList<>();
        for (Task t : tasks) {
            taskResponse.add(mapTaskId(t));
        }
        return new MultipleTaskResponseId(taskResponse);
    }

    private String getStatus(Task task) {
        return task.getStatus().toString();
    }

    private String getDifficulty(Task task) {
        int average = (task.getEstimationA() + task.getEstimationB() + task.getEstimationC())/3;
        String difficulty = null;
        if(average<2)
            difficulty = "EASY";
        else if(average<=4)
            difficulty = "MEDIUM";
        else if (average>=5)
            difficulty = "HARD";
        return difficulty;
    }
}
