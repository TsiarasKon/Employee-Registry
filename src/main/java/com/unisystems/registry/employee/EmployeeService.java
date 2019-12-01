package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.employee.search_employee_strategy.SearchEmployeeStrategyFactory;
import com.unisystems.registry.employee.search_employee_strategy.SearchEmployeeStratrgy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeMapper mapper;

    @Autowired
    private SearchEmployeeStrategyFactory factory;

    public EmployeeService( EmployeeMapper mapper,EmployeeRepository employeeRepository) {
        this.mapper = mapper;
        this.employeeRepository = employeeRepository;


    }

    public GenericResponse<MultipleEmployeeResponse> getAllEmployees() {
        Iterable<Employee> retrievedEmployees = employeeRepository.findAll();
        List<EmployeeResponse> employees = new ArrayList<>();

        for(Employee employee: retrievedEmployees){
            employees.add(mapper.mapEmployee(employee));
        }

        return new GenericResponse<>(new MultipleEmployeeResponse(employees));
    }

    public GenericResponse<MultipleEmployeeResponse> getEmployeesInCriteria(String criteria, long criteriaId) {
        Iterable<Employee> retrievedEmployees = employeeRepository.findAll();
        SearchEmployeeStratrgy stratrgy = factory.makeStrategyForCriteria(criteria);
        try {
            return new GenericResponse<>(mapper.mapEmployeeList(stratrgy.execute(criteriaId, retrievedEmployees)));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    public GenericResponse<EmployeeResponse> getEmployeeWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapEmployee(employeeRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Employee", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }
}
