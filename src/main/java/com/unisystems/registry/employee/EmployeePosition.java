package com.unisystems.registry.employee;

public enum EmployeePosition {
    SENIOR_ANALYST("Senior Analyst"),
    PROGRAMMER("Programmer"),
    DELIVERY_MANAGER("Delivery Manager"),
    ACCOUNTANT("Accountant"),
    SCRUM_MASTER("Scrum Master");

    private String str;

    EmployeePosition(String str) {this.str = str;}

    @Override
    public String toString() {
        return str;
    }
}
