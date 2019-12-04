package com.unisystems.registry.company;

import com.unisystems.registry.GenericError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class CompanyRequest {

    public String companyName;

    public CompanyRequest() {
    }

    public CompanyRequest(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public ResponseEntity<Object> validateRequest() {
        if (companyName == null) {
            return new ResponseEntity<>(
                    new GenericError(2, "Invalid request body", "Please provide companyName"),
                    HttpStatus.BAD_REQUEST
            );
        }
        return null;
    }
}
