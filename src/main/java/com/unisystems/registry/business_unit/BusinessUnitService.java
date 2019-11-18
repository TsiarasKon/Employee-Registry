package com.unisystems.registry.business_unit;

import com.unisystems.registry.GenericResponse;
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

    public void saveChanges(BusinessUnit businessUnit)
    {
        repository.save(businessUnit);
    }

/*    public BusinessUnitResponse getBusinessUnitById(Long id) throws InvalidIdException
    {
        return mapper.mapBusinessUnitResponseFromBusinessUnit(repository.findById(id)
                .orElseThrow(() -> new InvalidIdException("Business Unit", id)));
    }*/
}
