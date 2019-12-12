package com.unisystems.registry.employee.search_employee_strategy;

import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.business_unit.BusinessUnitService;
import com.unisystems.registry.employee.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SearchEmployeeByBusinessUnitStrategy implements SearchEmployeeStratrgy {
    @Autowired
    private BusinessUnitService service;

    @Override
    public List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) throws InvalidIdException {
        if (service.getBusinessUnitWithId(criteriaId).getError() != null) {
            throw new InvalidIdException("Business Unit", criteriaId);
        }
        List<Employee> employees = new ArrayList<>();
        for (Employee employee : allEmployees) {
            if (employee.getUnit().getDept().getBu().getId() == criteriaId)
                employees.add(employee);
        }
        return employees;
    }
}
