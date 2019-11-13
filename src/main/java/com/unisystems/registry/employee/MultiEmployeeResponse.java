package com.unisystems.registry.employee;

import java.util.List;

public class MultiEmployeeResponse {

    private List<EmployeeResponse> employeeResponses;

    public MultiEmployeeResponse(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }

    public List<EmployeeResponse> getEmployeeResponses() {
        return employeeResponses;
    }

    public void setEmployeeResponses(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }
}
