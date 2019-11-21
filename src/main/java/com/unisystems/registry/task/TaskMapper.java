package com.unisystems.registry.task;

import com.unisystems.registry.employee.Employee;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class TaskMapper {
    private int sum;
    private int average;

    public TaskResponseById mapTaskResponseFromTaskId(Task task){

        return new TaskResponseById(
                task.getId(),
                task.getTitle(),
                task.getDesc(),
                getDifficulty(task),
                task.getStatus(),
                getEmployeeFullName(task),
                task.getUpdates()
        );
    }

    private String getEmployeeFullName(Task task) {
        return task.getEmployee().getFirstName()+" "+task.getEmployee().getLastName();
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
