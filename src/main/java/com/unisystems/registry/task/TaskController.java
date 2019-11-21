package com.unisystems.registry.task;

import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasks")
    public ResponseEntity getAllTasks(){
        GenericResponse<MultipleTaskResponse> taskResponse = taskService.getAllTasks();
        return taskResponse.getResponseEntity(null, HttpStatus.OK);
    }

    @GetMapping("/getTaskById/{taskId}")
    public ResponseEntity getTaskById(@PathVariable long taskId){
        GenericResponse<MultipleTaskResponse> taskById = taskService.getTaskById(taskId);
        return taskById.getResponseEntity(null, HttpStatus.OK);
    }
}
