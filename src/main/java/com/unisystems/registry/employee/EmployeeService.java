package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.employee.search_employee_strategy.SearchEmployeeStrategyFactory;
import com.unisystems.registry.employee.search_employee_strategy.SearchEmployeeStratrgy;
import com.unisystems.registry.unit.Unit;
import com.unisystems.registry.unit.UnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UnitRepository unitRepository;

    @Autowired
    EmployeeMapper mapper;

    @Autowired
    private SearchEmployeeStrategyFactory factory;

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

    public Employee post(EmployeeRequest employeeRequest) throws InvalidIdException {
        Unit employeeUnit = unitRepository.findById(employeeRequest.getUnitId()).orElseThrow(()
                -> new InvalidIdException("Unit", employeeRequest.getUnitId()));
        Employee employee = new Employee(employeeRequest.getFirstName(), employeeRequest.getLastName(),
                employeeRequest.getAddress(), employeeRequest.getPhoneNumber(), employeeRequest.getRecruitmentDateValid(),
                employeeRequest.getExitDateValid(), employeeRequest.isStatusValid(),
                employeeRequest.getEmployeeContractTypeValid(), employeeRequest.getEmployeePositionValid());
        employee.setUnit(employeeUnit);
        return employeeRepository.save(employee);
    }

    public Employee put(EmployeeRequest employeeRequest, long id) throws InvalidIdException {
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Employee", id));
        Unit employeeUnit = unitRepository.findById(employeeRequest.getUnitId()).orElseThrow(()
                -> new InvalidIdException("Unit", employeeRequest.getUnitId()));
        employee.setUnit(employeeUnit);
        employee.setFirstName(employeeRequest.getFirstName());
        employee.setLastName(employeeRequest.getLastName());
        employee.setAddress(employeeRequest.getAddress());
        employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        employee.setRecruitmentDate(employeeRequest.getRecruitmentDateValid());
        employee.setExitDate(employeeRequest.getExitDateValid());
        employee.setStatus(employeeRequest.isStatusValid());
        employee.setEmployeeContractType(employeeRequest.getEmployeeContractTypeValid());
        employee.setEmployeePosition(employeeRequest.getEmployeePositionValid());
        return employeeRepository.save(employee);
    }

    public Employee patch(EmployeeRequest employeeRequest, long id) throws InvalidIdException {
        Employee employee = employeeRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Employee", id));
        if (employeeRequest.getUnitId() > 0) {
            Unit employeeUnit = unitRepository.findById(employeeRequest.getUnitId()).orElseThrow(()
                    -> new InvalidIdException("Unit", employeeRequest.getUnitId()));
            employee.setUnit(employeeUnit);
        }
        if (employeeRequest.getFirstName() != null) employee.setFirstName(employeeRequest.getFirstName());
        if (employeeRequest.getLastName() != null) employee.setLastName(employeeRequest.getLastName());
        if (employeeRequest.getAddress() != null) employee.setAddress(employeeRequest.getAddress());
        if (employeeRequest.getPhoneNumber() != null) employee.setPhoneNumber(employeeRequest.getPhoneNumber());
        if (employeeRequest.getRecruitmentDate() != null) employee.setRecruitmentDate(employeeRequest.getRecruitmentDateValid());
        if (employeeRequest.getExitDate() != null) employee.setExitDate(employeeRequest.getExitDateValid());
        if (employeeRequest.getStatus() != null) employee.setStatus(employeeRequest.isStatusValid());
        if (employeeRequest.getEmployeeContractType() != null) employee.setEmployeeContractType(employeeRequest.getEmployeeContractTypeValid());
        if (employeeRequest.getEmployeePosition() != null) employee.setEmployeePosition(employeeRequest.getEmployeePositionValid());
        return employeeRepository.save(employee);
    }
}
