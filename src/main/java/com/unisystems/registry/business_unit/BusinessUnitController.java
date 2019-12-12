package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;
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
        {
            return new ResponseEntity<>(
                    genericResponse.getError(),
                    null,
                    HttpStatus.BAD_REQUEST
            );
        }
        return new ResponseEntity<>(
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

//    @GetMapping("/BusinessUnits/{id}")
//    public ResponseEntity<Object> getBusinessUnitById(@PathVariable Long id)
//    {
//        GenericResponse<Optional<BusinessUnit>> response = service.getBusinessUnitById(id);
//        if(!response.getData().isPresent())
//        {
//            return new ResponseEntity(
//                    new GenericError(new Date(),404, "Not found", "Business Unit with this id: (" + id + ") does not exist"),
//                    null,
//                    HttpStatus.NOT_FOUND
//            );
//        }
//        return new ResponseEntity(
//                response.getData(),
//                null,
//                HttpStatus.OK
//        );
//    }
//
//    @PostMapping(value = "/BusinessUnits", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Object> createBusinessUnit(@Valid @RequestBody BusinessUnit businessUnit)
//    {
//        service.saveChanges(businessUnit);
//
//        URI uri = ServletUriComponentsBuilder
//                .fromCurrentRequest()
//                .path("/{id}")
//                .buildAndExpand(businessUnit.getId())
//                .toUri();
//
//        return ResponseEntity.created(uri).build();
//    }
//
//    @PutMapping("/BusinessUnits/{id}")
//    public ResponseEntity<Object> updateBusinessUnit(@Valid @RequestBody BusinessUnit businessUnit,
//                                                     @PathVariable long id)
//    {
//        GenericResponse<Optional<BusinessUnit>> updateResponse = service.getBusinessUnitById(id);
//        if(!updateResponse.getData().isPresent())
//            return ResponseEntity.notFound().build();
//
//        businessUnit.setId(id);
//
//        service.saveChanges(businessUnit);
//
//        return ResponseEntity.noContent().build();
//    }

    @PostMapping("/business-units")
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
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/business-units/{id}")
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
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/business-units/{id}")
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
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

}
