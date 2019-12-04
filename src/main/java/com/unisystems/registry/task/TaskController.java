package com.unisystems.registry.task;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.task.search_task_strategy.difficultyComparison;
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

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }
  
    @GetMapping("/tasks")
    public ResponseEntity getAllTasks(){
        GenericResponse<MultipleTaskResponse> taskResponse = taskService.getAllTasks();
        return taskResponse.getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/{taskId}")
    public ResponseEntity getTaskById(@PathVariable long taskId){
        GenericResponse<MultipleTaskResponseId> taskById = taskService.getTaskById(taskId);
        return taskById.getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/difficulty/{difficulty}")
    public ResponseEntity getTasksInCriteria(@PathVariable String difficulty) {
        if (! new difficultyComparison().checkIfInStructure(difficulty)) {
            return new ResponseEntity(
                    new GenericError(1, "Input Error", "Difficulty '" + difficulty + "' does not exist"),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return taskService.getTasksByDifficulty(difficulty, null).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/employees-num/{assignedEmployees}")
    public ResponseEntity getTasksByEmployees(@PathVariable Long assignedEmployees) {
        return taskService.getTasksByDifficulty(null, assignedEmployees).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/tasks/difficulty/{difficulty}/employees-num/{assignedEmployees}")
    public ResponseEntity getTasksInCriteria(@PathVariable String difficulty, @PathVariable long assignedEmployees) {
        if (! new difficultyComparison().checkIfInStructure(difficulty)) {
            return new ResponseEntity(
                    new GenericError(1, "Input Error", "Difficulty '" + difficulty + "' does not exist"),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return taskService.getTasksByDifficulty(difficulty, assignedEmployees).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }
}
