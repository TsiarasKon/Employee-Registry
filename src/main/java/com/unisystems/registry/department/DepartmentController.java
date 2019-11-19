package com.unisystems.registry.department;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    @GetMapping("/departments")
    public ResponseEntity getAllDepartments() {
        try {
            GenericResponse<MultipleDepartmentsResponse> response = service.getAllDepartments();
            if(response.getError() != null)
                return new ResponseEntity(
                        response.getError(),
                        null,
                        HttpStatus.BAD_REQUEST
                );

            return new ResponseEntity(
                    response.getData(),
                    null,
                    HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new  ResponseEntity(
                    new GenericError(0, "Error" ,"Something went wrong please try again"),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/departments/{id}")
    public ResponseEntity getCompanyById(@PathVariable long id) {
        return service.getDepartmentWithId(id).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

}
