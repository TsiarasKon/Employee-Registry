package com.unisystems.registry.department;

import com.unisystems.registry.GenericError;
import org.hibernate.validator.constraints.Range;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import javax.validation.constraints.NotEmpty;

public class DepartmentRequest {

//    @NotEmpty(message = "Please provide a Department name")
    private String departmentName;
//    @Range(min=1, message = "Please provide a Business Unit id")
    private long businessUnitId;

    public DepartmentRequest() {
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public long getBusinessUnitId() {
        return businessUnitId;
    }

    public void setBusinessUnitId(long businessUnitId) {
        this.businessUnitId = businessUnitId;
    }

    public ResponseEntity<Object> validateDepartmentRequest() {
        if (departmentName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide a Department name"),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (businessUnitId <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide a Department name"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return null;
    }
}
