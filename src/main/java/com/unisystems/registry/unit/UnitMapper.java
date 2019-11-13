package com.unisystems.registry.unit;

import org.springframework.stereotype.Component;

@Component
public class UnitMapper {
    public UnitResponse mapUnitResponseFromUnit(Unit unit){
        return new UnitResponse(
                unit.getId(),
                unit.getName(),
                unit.getDept()
        );
    }
}
