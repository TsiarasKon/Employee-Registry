package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;

import com.unisystems.registry.InvalidIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
public class UnitController {
    private UnitRepository repository;

    @Autowired
    private UnitService service;

    //public UnitController(UnitRepository repository){this.repository=repository;}

    public UnitController(UnitService service) {
        this.service = service;
    }

    @GetMapping("/units")
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

    @GetMapping("/units/{unitId}")
    public ResponseEntity getUnitById(@PathVariable Long unitId)
    {

            return service.getUnitWithId(unitId).getResponseEntity(null,HttpStatus.BAD_REQUEST);

    }

    @PostMapping("/units")
    public ResponseEntity<Object> postUnit(@RequestBody UnitRequest unitRequest) {
        ResponseEntity<Object> errorReturn = unitRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.post(unitRequest),
                    HttpStatus.CREATED
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PutMapping("/units/{id}")
    public ResponseEntity<Object> putUnit(@RequestBody UnitRequest unitRequest, @PathVariable long id) {
        if (service.getUnitWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Unit with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        ResponseEntity<Object> errorReturn = unitRequest.validateRequest();
        if (errorReturn != null) return errorReturn;
        try {
            return new ResponseEntity<>(
                    service.put(unitRequest, id),
                    HttpStatus.OK
            );
        } catch (InvalidIdException e) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", e.getMessage()),
                    HttpStatus.BAD_REQUEST
            );
        }
    }

    @PatchMapping("/units/{id}")
    public ResponseEntity<Object> patchUnit(@RequestBody UnitRequest unitRequest, @PathVariable long id) {
        if (service.getUnitWithId(id).getError() != null) {
            return new ResponseEntity<>(
                    new GenericError(1, "Invalid id", "Unit with id '" + id + "' does not exist"),
                    HttpStatus.NOT_FOUND
            );
        }
        try {
            return new ResponseEntity<>(
                    service.patch(unitRequest, id),
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
