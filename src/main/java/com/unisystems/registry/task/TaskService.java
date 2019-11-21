package com.unisystems.registry.task;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    @Autowired
    TaskRepository taskRepository;

    @Autowired
    TaskMapper mapper;

    public GenericResponse<MultipleTaskResponse> getAllTasks() {
        Iterable<Task> retrievedTasks = taskRepository.findAll();
        List<TaskResponse> tasks = new ArrayList<>();

        for(Task task : retrievedTasks){
            tasks.add(mapper.MapTask(task));
        }

        return new GenericResponse<>(new MultipleTaskResponse(tasks));
    }

    public GenericResponse<TaskResponse> getTaskWithId(long id) {
        try {
            return new GenericResponse<>(mapper.MapTaskId(taskRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Task", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    public GenericResponse<MultipleTaskResponse> getTaskById(long taskId){
        try {
            List<TaskResponse> tasks = getTaskResponses(taskId);
            return new GenericResponse<>((new MultipleTaskResponse(tasks)));
        } catch (InvalidIdException e){
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    private List<TaskResponse> getTaskResponses(long taskId) throws InvalidIdException {
        List<TaskResponse> tasks = new ArrayList<>();

        if (getTaskWithId(taskId).getError() != null) {
            throw new InvalidIdException("Task", taskId);
        }

        tasks.add(getTaskWithId(taskId).getData());

        return tasks;
    }
}
