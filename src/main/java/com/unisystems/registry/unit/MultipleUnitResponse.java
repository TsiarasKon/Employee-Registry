package com.unisystems.registry.unit;

import java.util.List;

public class MultipleUnitResponse {
    private List<UnitResponse> unitsResponses;

    public MultipleUnitResponse(List<UnitResponse> unitsResponses) {
        this.unitsResponses = unitsResponses;
    }

    public List<UnitResponse> getUnitsResponses() {
        return unitsResponses;
    }

    public void setUnitsResponses(List<UnitResponse> unitsResponses) {
        this.unitsResponses = unitsResponses;
    }
}
