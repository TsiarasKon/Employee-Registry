package com.unisystems.registry.department;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
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


    public DepartmentService(DepartmentMapper mapper, DepartmentRepository repository) {
        this.mapper = mapper;
        this.repository = repository;
    }

    public GenericResponse<MultipleDepartmentsResponse> getAllDepartments() {
        Iterable<Department> retrievedDepartments = repository.findAll();
        List<DepartmentResponse> departments = new ArrayList<>();

        for (Department department : retrievedDepartments) {
            departments.add(mapper.mapDepartmentResponseFromDepartment(department));
        }
        return new GenericResponse<>(new MultipleDepartmentsResponse(departments));

    }

    public GenericResponse<DepartmentResponse> getDepartmentWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapDepartmentResponseFromDepartment( repository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Department", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

}
