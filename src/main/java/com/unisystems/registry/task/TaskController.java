package com.unisystems.registry.task;

import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {

    @Autowired
    TaskService service;

    @GetMapping("/allTasks")
    public ResponseEntity getAllTasks() {

        GenericResponse<MultipleTaskResponse> response = service.getAllTasks();
        if (response.getError() != null)
            return new ResponseEntity(response.getError(), null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(response.getData(), null, HttpStatus.OK);
    }

    @GetMapping("/tasks/{taskId}")
    public MultiTaskResponseById getTaskWithId(@PathVariable long taskId)
    {
        return new MultiTaskResponseById(service.getTaskWithId(taskId));
    }

//    @GetMapping("/{uCriteria}/{id}")
//    public AllEmployeesResponse getEmployesbyCriteriaAndId(@PathVariable String uCriteria,@PathVariable Long id) {
//        return new AllEmployeesResponse(service.getEmployeesByCriteriaAndId(uCriteria,id));
//
//    }



}



