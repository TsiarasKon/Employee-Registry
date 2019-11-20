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

    private String getStatus(Task task) {
        return task.getStatus().toString();
    }

    private String getDifficulty(Task task) {
        int difficultyNumber = (task.getEstimationa() + task.getEstimationb() + task.getEstimationc())/3;
        String difficulty = null;
        if(difficultyNumber<2)
            difficulty = "EASY";
        else if(difficultyNumber<=4)
            difficulty = "MEDIUM";
        else if (difficultyNumber>5)
            difficulty = "HARD";
        return difficulty;
    }
}
