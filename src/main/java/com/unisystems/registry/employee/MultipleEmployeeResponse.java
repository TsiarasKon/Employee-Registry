package com.unisystems.registry.employee;

import java.util.List;

public class MultipleEmployeeResponse {

    private List<EmployeeResponse> employeeResponses;

    public MultipleEmployeeResponse(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }

    public List<EmployeeResponse> getEmployeeResponses() {
        return employeeResponses;
    }

    public void setEmployeeResponses(List<EmployeeResponse> employeeResponses) {
        this.employeeResponses = employeeResponses;
    }
}
