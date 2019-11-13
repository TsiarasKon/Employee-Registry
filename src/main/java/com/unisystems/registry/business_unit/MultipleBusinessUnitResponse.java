package com.unisystems.registry.business_unit;

import java.util.List;

public class MultipleBusinessUnitResponse
{
    List<BusinessUnitResponse> businessUnitResponse;

    public MultipleBusinessUnitResponse(List<BusinessUnitResponse> businessUnitResponse)
    {
        this.businessUnitResponse = businessUnitResponse;
    }

    public List<BusinessUnitResponse> getBusinessUnitResponse() {
        return businessUnitResponse;
    }

    public void setBusinessUnitResponse(List<BusinessUnitResponse> businessUnitResponse) {
        this.businessUnitResponse = businessUnitResponse;
    }
}
