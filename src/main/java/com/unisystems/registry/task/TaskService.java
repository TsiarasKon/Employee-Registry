package com.unisystems.registry.task;

import com.unisystems.registry.GenericResponse;
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
}
