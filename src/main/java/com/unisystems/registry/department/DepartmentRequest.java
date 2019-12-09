package com.unisystems.registry.department;

import com.unisystems.registry.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class DepartmentRequest {

    private String departmentName;
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

    public ResponseEntity<Object> validateRequest() {
        if (departmentName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide departmentName"),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (businessUnitId <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide businessUnitId"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return null;
    }
}
