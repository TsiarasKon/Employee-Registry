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
@RequestMapping("Employees")
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/list")
    public ResponseEntity getAllEmployees(){
        GenericResponse<MultipleEmployeeResponse> employeeResponse = service.getAllEmployees();
        return employeeResponse.getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/{id}")
    public ResponseEntity getEmployeeWithId(@PathVariable long id) {
        return service.getEmployeeWithId(id).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/In/{criteria}/{criteriaId}")
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
}
