package com.unisystems.registry.employee.search_employee_strategy;

import com.unisystems.registry.employee.Employee;

import java.util.ArrayList;
import java.util.List;

public class SearchEmployeeByDepartmentStrategy implements SearchEmployeeStratrgy {
    @Override
    public List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) {
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getUnit().getDept().getId() == criteriaId)
                employees.add(employee);
        }
        return employees;
    }
}
