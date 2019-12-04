package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class BusinessUnitRequest {

    private String businessUnitName;
    private long companyId;

    public BusinessUnitRequest() {
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }

    public ResponseEntity<Object> validateRequest() {
        if (businessUnitName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide businessUnitName"),
                    HttpStatus.BAD_REQUEST
            );
        }
        if (companyId <= 0) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide companyId"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return null;
    }
}
