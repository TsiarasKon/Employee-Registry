package com.unisystems.registry.employee.search_employee_strategy;

import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.employee.Employee;

import java.util.List;

public interface SearchEmployeeStratrgy {
    List<Employee> execute(Long criteriaId, Iterable<Employee> allEmployees) throws InvalidIdException;
}
