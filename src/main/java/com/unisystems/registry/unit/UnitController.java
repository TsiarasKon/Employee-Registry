package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/Units")
public class UnitController {
    private final   UnitRepository repository;

    UnitController(UnitRepository repository){this.repository=repository;}

    @Autowired
    UnitService service;


    @GetMapping("/list")
    public ResponseEntity getAllUnits(){
        try{
            GenericResponse<MultipleUnitResponse> response=service.getAllUnits();
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

    @GetMapping("/{unitId}")
    public ResponseEntity getUnitById(@PathVariable Long unitId)
    {

            return service.getUnitWithId(unitId).getResponseEntity(null,HttpStatus.BAD_REQUEST);

    }

}
