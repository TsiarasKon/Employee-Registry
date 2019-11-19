package com.unisystems.registry.employee.search_employee_strategy;

import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.department.DepartmentService;
import com.unisystems.registry.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEmployeeByDepartmentStrategy implements SearchEmployeeStratrgy {
    @Autowired
    private DepartmentService service;

    @Override
    public List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) throws InvalidIdException {
        if (service.getDepartmentWithId(criteriaId).getError() != null) {
            throw new InvalidIdException("Department", criteriaId);
        }
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getUnit().getDept().getId() == criteriaId)
                employees.add(employee);
        }
        return employees;
    }
}
