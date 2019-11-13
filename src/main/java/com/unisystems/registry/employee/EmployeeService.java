package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
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

    public GenericResponse<MultiEmployeeResponse> getAllEmployees() {
        Iterable<Employee> retrievedEmployees = employeeRepository.findAll();
        List<EmployeeResponse> employees = new ArrayList<>();

        for(Employee employee: retrievedEmployees){
            employees.add(mapper.mapEmployee(employee));
        }

        if(employees.isEmpty())
            return new GenericResponse(new GenericError(0,"Empty List","There are no employees in the database."));

        return new GenericResponse<>(new MultiEmployeeResponse(employees));
    }
}
