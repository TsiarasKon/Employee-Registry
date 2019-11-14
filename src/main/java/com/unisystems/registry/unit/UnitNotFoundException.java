package com.unisystems.registry.unit;

public class UnitNotFoundException extends RuntimeException {
    public UnitNotFoundException(Long unitId){
        super("Could not find unit by id: " +unitId);
    }
}
