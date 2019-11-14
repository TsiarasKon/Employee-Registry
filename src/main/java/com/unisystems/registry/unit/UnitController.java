package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.business_unit.BusinessUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class UnitController {
    private final   UnitRepository repository;

    UnitController(UnitRepository repository){this.repository=repository;}

    @Autowired
    UnitService service;


    @GetMapping("/Units")
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

    @GetMapping("/Unit/{unitId}")
    public ResponseEntity getUnitById(@PathVariable Long unitId)
    {
        try
        {
            GenericResponse<Optional<Unit>> response = service.getUnitsByUnitId(unitId);

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
                    new GenericError(500, "Internal Server Error", "Something went horribly wrong"),
                    HttpStatus.INTERNAL_SERVER_ERROR
            );
        }

    }

    @GetMapping("/unit/{unitId}")
    public Unit findUnitById(@PathVariable Long unitId) {

        return repository.findById(unitId)
                .orElseThrow(() -> new UnitNotFoundException(unitId));



    }




}
