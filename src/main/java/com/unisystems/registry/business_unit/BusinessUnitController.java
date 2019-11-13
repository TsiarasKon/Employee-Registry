package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class BusinessUnitController
{

    @Autowired
    private BusinessUnitService service;

    @GetMapping("/BusinessUnits")
    public ResponseEntity getAllBusinessUnits()
    {
        try
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
        catch (Exception ex)
        {
            ex.printStackTrace();
            return new ResponseEntity(
              new GenericError(500, "Internal Server Error", "Something went horrible wrong"),
              null,
              HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/BusinessUnit/{id}")
    public ResponseEntity getBusinessUnitById(@PathVariable Long id)
    {
        try
        {
            GenericResponse<Optional<BusinessUnit>> response = service.getBusinessUnitById(id);

            return new ResponseEntity(
                    response.getData(),
                    null,
                    HttpStatus.OK
            );
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
            return new ResponseEntity(
                    new GenericError(500, "Internal Server Error", "Something went horrible wrong"),
                    HttpStatus.BAD_REQUEST
            );
        }

    }
}
