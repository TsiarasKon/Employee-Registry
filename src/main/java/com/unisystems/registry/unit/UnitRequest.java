package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class UnitRequest {

    private String unitName;
    private long departmentId;

    public UnitRequest() {
    }

    public UnitRequest(String name, long departmentId) {
        this.unitName = name;
        this.departmentId = departmentId;
    }

    public String getUnitName() {
        return unitName;
    }

    public void setUnitName(String unitName) {
        this.unitName = unitName;
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    public ResponseEntity<Object> validateRequest() {
        if (unitName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide unitName"),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (departmentId <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide departmentId"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return null;
    }
}
