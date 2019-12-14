package com.unisystems.registry.task;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.task.search_task_strategy.difficultyComparison;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping("/tasks")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER')" +
                  " or hasRole('ROLE_DEPARTMENT_MANAGER') or hasRole('ROLE_UNIT_MANAGER')")
    public ResponseEntity<Object> postTask(@RequestBody TaskRequest taskRequest) {
        ResponseEntity<Object> errorReturn = taskRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    taskService.post(taskRequest),
                    HttpStatus.CREATED
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        } catch (InterUnitTaskException e) {
            return new ResponseEntity<>(
                    new GenericError(3, "Task across different units", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/tasks/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER')" +
                  " or hasRole('ROLE_DEPARTMENT_MANAGER') or hasRole('ROLE_UNIT_MANAGER')")
    public ResponseEntity<Object> putTask(@RequestBody TaskRequest taskRequest, @PathVariable long id) {
        if (taskService.getTaskById(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Department with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseEntity<Object> errorReturn = taskRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    taskService.put(taskRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        } catch (InterUnitTaskException e) {
            return new ResponseEntity<>(
                    new GenericError(3, "Task across different units", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/tasks/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER')" +
                  " or hasRole('ROLE_DEPARTMENT_MANAGER') or hasRole('ROLE_UNIT_MANAGER')")
    public ResponseEntity<Object> patchTask(@RequestBody TaskRequest taskRequest, @PathVariable long id) {
        if (taskService.getTaskById(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Department with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            return new ResponseEntity<>(
                    taskService.patch(taskRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        } catch (InterUnitTaskException e) {
            return new ResponseEntity<>(
                    new GenericError(3, "Task across different units", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
