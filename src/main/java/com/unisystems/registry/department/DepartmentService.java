package com.unisystems.registry.department;

import com.unisystems.registry.GenericResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Autowired
    private DepartmentRepository repository;


    public GenericResponse<MultipleDepartmentsResponse> getAllDepartments() {
        Iterable<Department> retrievedDepartments = repository.findAll();
        List<DepartmentResponse> departments = new ArrayList<>();

        for (Department department : retrievedDepartments) {
            departments.add(mapper.mapDepartmentResponseFromDepartment(department));
        }
        return new GenericResponse<>(new MultipleDepartmentsResponse(departments));

    }
}
