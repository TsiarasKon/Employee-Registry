package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessUnitService
{

   private BusinessUnitMapper mapper;
   private BusinessUnitRepository repository;

    public BusinessUnitService(BusinessUnitMapper mapper, BusinessUnitRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse<MultipleBusinessUnitResponse> getAllBusinessUnits()
    {
        Iterable<BusinessUnit> retrievedBusinessUnits = repository.findAll();
        List<BusinessUnitResponse> businessUnits = new ArrayList<>();

        for(BusinessUnit businessUnit: retrievedBusinessUnits)
        {
            businessUnits.add(mapper.mapBusinessUnitResponseFromBusinessUnit(businessUnit));
        }
        return new GenericResponse<>(new MultipleBusinessUnitResponse(businessUnits));
    }

    public GenericResponse<Optional<BusinessUnit>> getBusinessUnitById(Long id)
    {
        return new GenericResponse<>(repository.findById(id));

    }

    public GenericResponse<BusinessUnitResponse> getBusinessUnitWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapBusinessUnitResponseFromBusinessUnit( repository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Business Unit", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }
}
