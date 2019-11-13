package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.StructureUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("/allEmployees")
    public ResponseEntity getAllEmployees(){
        GenericResponse<MultipleEmployeeResponse> employeeResponse = service.getAllEmployees();

        if(employeeResponse.getError() != null){
            return new ResponseEntity(
                    employeeResponse.getError(),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        else{
            return new ResponseEntity(
                    employeeResponse.getData(),
                    null,
                    HttpStatus.OK
            );
        }
    }

    @GetMapping("/employeesIn/{criteria}/{criteriaId}")
    public ResponseEntity getEmployeesInCriteria(@PathVariable String criteria, @PathVariable long criteriaId) {
        if (! new StructureUtil().checkIfInStructure(criteria)) {       // invalid criteria
            return new ResponseEntity(
                    new GenericError(1, "Input Error", "Criteria '" + criteria + "' does not exist"),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        GenericResponse<MultipleEmployeeResponse> employeeResponse = service.getEmployeesInCriteria(criteria, criteriaId);
        if (employeeResponse.getError() != null){
            return new ResponseEntity(
                    employeeResponse.getError(),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        } else {
            return new ResponseEntity(
                    employeeResponse.getData(),
                    null,
                    HttpStatus.OK
            );
        }
    }
}
