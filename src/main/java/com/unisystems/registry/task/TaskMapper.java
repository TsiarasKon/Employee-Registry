package com.unisystems.registry.task;

import org.springframework.stereotype.Component;

@Component
public class TaskMapper {
    private int sum;
    private int average;

    public TaskResponseById mapTaskResponseFromTaskiD(Task task){

        return new TaskResponseById(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                task.getStatus(),
                task.getEmployee()

        );
    }


        public TaskResponse mapTaskResponseFromTask(Task task){

        return new TaskResponse(
              task.getId(),
                task.getTitle(),
                task.getDesc(),
               getDifficulty(task),
                task.getStatus()
        );
    }

    private String getDifficulty(Task task) {
        sum=task.getEstimationA()+task.getEstimationB()+task.getEstimationC();
        average=sum/3;
        if(average<2)
            return "EASY";
        else if (average<=4)
            return "MEDIUM";
        else
            return "HARD";
    }

}
