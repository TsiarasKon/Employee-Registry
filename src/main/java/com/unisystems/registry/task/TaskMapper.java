package com.unisystems.registry.task;

import com.unisystems.registry.employee.Employee;
import com.unisystems.registry.employee.EmployeeMapper;
import com.unisystems.registry.employee.EmployeeResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class TaskMapper {

    @Autowired
    private EmployeeMapper employeeMapper;

    public TaskResponse mapTask(Task task){
        return new TaskResponse(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                getDifficulty(task),
                getStatus(task)
        );
    }

    public TaskResponseId mapTaskId(Task task){
        return new TaskResponseId(
                task.getId(),
                task.getTitle(),
                task.getDescription(),
                getDifficulty(task),
                getStatus(task),
                getAssignedEmployees(task),
                task.getUpdates()
        );
    }

    private List<EmployeeResponse> getAssignedEmployees(Task task) {
        List<EmployeeResponse> employeeResponses = new ArrayList<>();
        for(Employee e : task.getAssignedEmployee())
            employeeResponses.add(employeeMapper.mapEmployee(e));
        return employeeResponses;
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
