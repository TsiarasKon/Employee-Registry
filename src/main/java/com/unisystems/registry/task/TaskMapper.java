package com.unisystems.registry.task;


import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public TaskResponse MapTask(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                getStatus(task)
        );
    }

    public TaskResponse MapTaskId(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                getStatus(task),
                task.getAssignedEmployee(),
                task.getUpdates()
        );
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
