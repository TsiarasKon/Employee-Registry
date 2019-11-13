package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UnitController {

    @Autowired
    UnitService service;

    //@GetMapping("/allUnits")
    //public MultipleUnitResponse getAllUnits(){return new MultipleUnitResponse(service.getAllUnits());}
    @GetMapping("/allUnits")
    public ResponseEntity getAllUnits(){
        try{
            GenericResponse<MultipleUnitResponse> response=service.getAllUnits();
            return new ResponseEntity(
                    response,
                    null,
                    HttpStatus.OK
            );
        }catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity(
                    new GenericError(0,"Error","Please try again"),
                    null,
                    HttpStatus.OK
            );
        }
    }
    @GetMapping("/allUnitsById/{unitId}")
    public ResponseEntity getUnitById(@PathVariable Long unitId){
        try{
            return new ResponseEntity(
                    new MultipleUnitResponse(service.getUnitsByUnitId(unitId)),
                    null,
                    HttpStatus.OK
            );
        }catch (Exception e){
            e.printStackTrace();

            return new ResponseEntity(
                    new GenericError(0,"Error","Something went wrong"),
                    null,
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }
    }
}
