package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.StructureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/employees")
    public ResponseEntity getAllEmployees(){
        GenericResponse<MultipleEmployeeResponse> employeeResponse = service.getAllEmployees();
        return employeeResponse.getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employees/{id}")
    public ResponseEntity getEmployeeWithId(@PathVariable long id) {
        return service.getEmployeeWithId(id).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/employees-in/{criteria}/{criteriaId}")
    public ResponseEntity getEmployeesInCriteria(@PathVariable String criteria, @PathVariable long criteriaId) {
        if (! new StructureUtil().checkIfInStructure(criteria)) {       // invalid criteria
            return new ResponseEntity(
                    new GenericError(1, "Input Error", "Criteria '" + criteria + "' does not exist"),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return service.getEmployeesInCriteria(criteria, criteriaId).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/employees")
    public ResponseEntity<Object> putEmployee(@RequestBody EmployeeRequest employeeRequest) {
        ResponseEntity<Object> errorReturn = employeeRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.post(employeeRequest),
                    HttpStatus.CREATED
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/employees/{id}")
    public ResponseEntity<Object> putEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable long id) {
        if (service.getEmployeeWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Employee with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseEntity<Object> errorReturn = employeeRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.put(employeeRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/employees/{id}")
    public ResponseEntity<Object> patchEmployee(@RequestBody EmployeeRequest employeeRequest, @PathVariable long id) {
        if (service.getEmployeeWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Employee with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            return new ResponseEntity<>(
                    service.patch(employeeRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }
}
