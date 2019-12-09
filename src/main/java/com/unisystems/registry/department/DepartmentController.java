package com.unisystems.registry.department;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class DepartmentController {

    @Autowired
    private DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

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
    public ResponseEntity getDepartmentWithId(@PathVariable long id) {
        return service.getDepartmentWithId(id).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/departments")
    public ResponseEntity<Object> postDepartment(@RequestBody DepartmentRequest deptRequest) {
        ResponseEntity<Object> errorReturn = deptRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.post(deptRequest),
                    HttpStatus.CREATED
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/departments/{id}")
    public ResponseEntity<Object> putDepartment(@RequestBody DepartmentRequest deptRequest, @PathVariable long id) {
        if (service.getDepartmentWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Department with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseEntity<Object> errorReturn = deptRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.put(deptRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/departments/{id}")
    public ResponseEntity<Object> patchDepartment(@RequestBody DepartmentRequest deptRequest, @PathVariable long id) {
        if (service.getDepartmentWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Department with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            return new ResponseEntity<>(
                    service.patch(deptRequest, id),
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
