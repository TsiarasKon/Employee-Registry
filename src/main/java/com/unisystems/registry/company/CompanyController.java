package com.unisystems.registry.company;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {

    CompanyService service;

    public CompanyController(CompanyService service) {
        this.service = service;
    }

    @GetMapping("/companies")
    public ResponseEntity getAllCompanies() {

        GenericResponse<MultipleCompaniesResponse> response = service.getAllCompany();
        if (response.getError() != null)
            return new ResponseEntity(response.getError(), null, HttpStatus.BAD_REQUEST);

        return new ResponseEntity(response.getData(), null, HttpStatus.OK);

    }

    @GetMapping("/companies/{id}")
    public ResponseEntity getCompanyById(@PathVariable long id)
    {
        return service.getCompanyWithId(id).getResponseEntity(null,HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/companies")
    public ResponseEntity<Object> putCompany(@RequestBody CompanyRequest companyRequest) {
        ResponseEntity<Object> errorReturn = companyRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        return new ResponseEntity<>(
                service.post(companyRequest),
                HttpStatus.CREATED
        );
    }

    @PutMapping("/companies/{id}")
    public ResponseEntity<Object> putCompany(@RequestBody CompanyRequest companyRequest, @PathVariable long id) {
        if (service.getCompanyWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Company with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseEntity<Object> errorReturn = companyRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.put(companyRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/companies/{id}")
    public ResponseEntity<Object> patchCompany(@RequestBody CompanyRequest companyRequest, @PathVariable long id) {
        if (service.getCompanyWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Company with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            return new ResponseEntity<>(
                    service.patch(companyRequest, id),
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


