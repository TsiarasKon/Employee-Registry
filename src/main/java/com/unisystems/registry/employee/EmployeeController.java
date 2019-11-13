package com.unisystems.registry.employee;

import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class EmployeeController {

    @Autowired
    EmployeeService service;

    @GetMapping("allEmployees")
    public ResponseEntity getAllEmployees(){
        GenericResponse<MultiEmployeeResponse> employeeResponse = service.getAllEmployees();

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
}
