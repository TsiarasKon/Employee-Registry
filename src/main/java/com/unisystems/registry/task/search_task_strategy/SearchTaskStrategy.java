package com.unisystems.registry.task.search_task_strategy;

import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.task.Task;
import com.unisystems.registry.task.TaskResponse;

import java.util.List;

public interface SearchTaskStrategy {
    List<Task> execute(Long assignedEmployeesNumber, Iterable<Task> tasks) throws InvalidIdException;
}
