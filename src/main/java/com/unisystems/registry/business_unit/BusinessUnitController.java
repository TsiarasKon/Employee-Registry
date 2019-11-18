package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

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

    @GetMapping("/BusinessUnits")
    public ResponseEntity<Object> getAllBusinessUnits()
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

    @GetMapping("/BusinessUnit/{id}")
    public ResponseEntity<Object> getBusinessUnitById(@PathVariable Long id)
    {
        GenericResponse<Optional<BusinessUnit>> response = service.getBusinessUnitById(id);
        if(!response.getData().isPresent())
        {
            return new ResponseEntity(
                    new GenericError(new Date(),404, "Not found", "Business Unit with this id: (" + id + ") does not exist"),
                    null,
                    HttpStatus.NOT_FOUND
            );
        }
             return new ResponseEntity(
                    response.getData(),
                    null,
                    HttpStatus.OK
            );
    }

    @PostMapping(value = "/BusinessUnit", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Object> createBusinessUnit(@Valid @RequestBody BusinessUnit businessUnit)
    {
        service.saveChanges(businessUnit);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(businessUnit.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/BusinessUnit/{id}")
    public ResponseEntity<Object> updateBusinessUnit(@Valid @RequestBody BusinessUnit businessUnit,
                                                     @PathVariable long id)
    {
        GenericResponse<Optional<BusinessUnit>> updateResponse = service.getBusinessUnitById(id);
        if(!updateResponse.getData().isPresent())
            return ResponseEntity.notFound().build();

        businessUnit.setId(id);

        service.saveChanges(businessUnit);

        return ResponseEntity.noContent().build();
    }


/*    @GetMapping("/BusinessUnit/{id}")
    public ResponseEntity getBusinessUnitById(@PathVariable Long id)
    {
        try
        {
            BusinessUnitResponse response = service.getBusinessUnitById(id);
            return new ResponseEntity(
                    response,
                    null,
                    HttpStatus.OK
            );
        }
        catch(InvalidIdException ex)
        {
            return new ResponseEntity(
                    new GenericError(404, "Not found", ex.getMessage()),
                    null,
                    HttpStatus.NOT_FOUND
            );
        }
    }*/
}
