package com.unisystems.registry.employee;

public enum EmployeeContractType {
    INTERNAL("Company Internal"),
    EXTERNAL("External");

    private String str;

    EmployeeContractType(String str) {this.str = str;}

    @Override
    public String toString() {
        return str;
    }
}

