package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class BusinessUnitController
{

    @Autowired
    private BusinessUnitService service;

    public BusinessUnitController(BusinessUnitService service) {
        this.service = service;
    }

    @GetMapping("/business-units")
    public ResponseEntity getAllBusinessUnits()
    {
        GenericResponse<MultipleBusinessUnitResponse> genericResponse = service.getAllBusinessUnits();
        if(genericResponse.getError() != null)
            return new ResponseEntity(
              genericResponse.getError(),
              null,
              HttpStatus.BAD_REQUEST
            );
        return new ResponseEntity(
                genericResponse.getData(),
                null,
                HttpStatus.OK
        );
    }

    @GetMapping("/business-units/{id}")
    public ResponseEntity getBusinessUnitById(@PathVariable Long id)
    {
        return service.getBusinessUnitWithId(id).getResponseEntity(null, HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/business-units")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER') ")
    public ResponseEntity<Object> postBusinessUnit(@RequestBody BusinessUnitRequest buRequest) {
        ResponseEntity<Object> errorReturn = buRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.post(buRequest),
                    HttpStatus.CREATED
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/business-units/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER') ")
    public ResponseEntity<Object> putBusinessUnit(@RequestBody BusinessUnitRequest buRequest, @PathVariable long id) {
        if (service.getBusinessUnitById(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Business Unit with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseEntity<Object> errorReturn = buRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.put(buRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/business-units/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_COMPANY_MANAGER') or hasRole('ROLE_BUSINESS_MANAGER') ")
    public ResponseEntity<Object> patchBusinessUnit(@RequestBody BusinessUnitRequest buRequest, @PathVariable long id) {
        if (service.getBusinessUnitById(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Business Unit with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            return new ResponseEntity<>(
                    service.patch(buRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
