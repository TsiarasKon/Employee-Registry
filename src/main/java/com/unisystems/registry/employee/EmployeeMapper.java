package com.unisystems.registry.employee;

import org.springframework.stereotype.Component;

@Component
public class EmployeeMapper {

    public EmployeeResponse mapEmployee(Employee employee) {
        return new EmployeeResponse(
                employee.getId(),
                employee.getRecordNum(),
                getFullName(employee),
                employee.getPhoneNumber(),
                getWorkingPeriod(employee),
                getContractType(employee),
                getPosition(employee),
                isStatus(employee),
                getUnitName(employee)
        );
    }

    private String isStatus(Employee employee) {
        return employee.isStatus() ? "active" : "inactive";
    }

    private String getUnitName(Employee employee) {
        return employee.getUnit().getName();
    }

    private String getPosition(Employee employee) {
        return employee.getEmployeePosition().toString();
    }

    private String getContractType(Employee employee) {
        return employee.getEmployeeContractType().toString();
    }

    private String getWorkingPeriod(Employee employee) {
        if(employee.getExitDate() == null)
            return employee.getRecruitmentDate() + " - present";
        else
            return employee.getRecruitmentDate() + " - " + employee.getExitDate();
    }

    private String getFullName(Employee employee) {
        return employee.getFirstName() + " " + employee.getLastName();
    }
}