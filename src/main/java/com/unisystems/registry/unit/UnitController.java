package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class UnitController {
    private UnitRepository repository;
    UnitService service;

    //public UnitController(UnitRepository repository){this.repository=repository;}

    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping("/Units")
    public ResponseEntity getAllUnits(){
        try{
            GenericResponse<MultipleUnitResponse> response=service.getAllUnits();
            if(response.getError() != null)
                return new ResponseEntity(
                        response.getError(),
                        null,
                        HttpStatus.BAD_REQUEST
                );

            return new ResponseEntity(
                    response.getData(),
                    null,
                    HttpStatus.OK
            );
        }catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new GenericError(0,"Error","Please try again"),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }

    @GetMapping("/Unit/{unitId}")
    public ResponseEntity getUnitById(@PathVariable Long unitId)
    {

            return service.getUnitWithId(unitId).getResponseEntity(null,HttpStatus.BAD_REQUEST);

    }

}
