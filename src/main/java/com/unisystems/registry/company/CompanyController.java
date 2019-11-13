package com.unisystems.registry.company;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping
public class CompanyController {

    @Autowired
    CompanyService service;


    @GetMapping("/allCompanies")
    public ResponseEntity getAllCompanies() {

        GenericResponse<MultipleCompaniesResponse> response = service.getAllCompany();
        if (response.getError() != null)
            return new ResponseEntity(response.getError(), null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(response.getData(), null, HttpStatus.OK);

    }

}


