package com.unisystems.registry.business_unit;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BusinessUnitMapper
{


    public BusinessUnitResponse mapBusinessUnitResponseFromBusinessUnit(BusinessUnit businessUnit)
    {
        return new BusinessUnitResponse(
                businessUnit.getId(),
                businessUnit.getName(),
                businessUnit.getCompany().getName()
        );
    }
}
