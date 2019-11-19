package com.unisystems.registry.employee.search_employee_strategy;

import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.employee.Employee;
import com.unisystems.registry.unit.UnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEmployeeByUnitStrategy implements SearchEmployeeStratrgy {
    @Autowired
    private UnitService service;

    @Override
    public List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) throws InvalidIdException {
        if (service.getUnitWithId(criteriaId).getError() != null) {
            throw new InvalidIdException("Unit", criteriaId);
        }
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getUnit().getId() == criteriaId)
                employees.add(employee);
        }
        return employees;
    }
}
