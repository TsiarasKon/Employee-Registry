package com.unisystems.registry.task.search_task_strategy;

import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.task.Task;
import com.unisystems.registry.task.TaskMapper;
import com.unisystems.registry.task.TaskRepository;
import com.unisystems.registry.task.TaskResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEasyTaskStrategy implements SearchTaskStrategy{
    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskMapper mapper;

    @Override
    public List<Task> execute(Long assignedEmployeesNumber, Iterable<Task> tasks) throws InvalidIdException {
        List<Task> allTasks = new ArrayList<>();
        for (Task task : tasks) {
            if (mapper.mapTaskId(task).getDifficulty().toLowerCase().equals("easy") && assignedEmployeesNumber==null)
                allTasks.add(task);
            else if(mapper.mapTaskId(task).getDifficulty().toLowerCase().equals("easy") && assignedEmployeesNumber==mapper.mapTaskId(task).getAssignedEmployees().size())
                allTasks.add(task);
        }
        return allTasks;
    }
}