package com.unisystems.registry.department;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.business_unit.BusinessUnit;
import com.unisystems.registry.business_unit.BusinessUnitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class DepartmentService {

    @Autowired
    private DepartmentMapper mapper;

    @Autowired
    private BusinessUnitRepository businessUnitRepository;

    @Autowired
    private DepartmentRepository departmentRepository;


    public DepartmentService(DepartmentMapper mapper, DepartmentRepository repository) {
        this.mapper = mapper;
        this.departmentRepository = repository;
    }

    public GenericResponse<MultipleDepartmentsResponse> getAllDepartments() {
        Iterable<Department> retrievedDepartments = departmentRepository.findAll();
        List<DepartmentResponse> departments = new ArrayList<>();

        for (Department department : retrievedDepartments) {
            departments.add(mapper.mapDepartmentResponseFromDepartment(department));
        }
        return new GenericResponse<>(new MultipleDepartmentsResponse(departments));

    }

    public GenericResponse<DepartmentResponse> getDepartmentWithId(long id) {
        try {
            return new GenericResponse<>(mapper.mapDepartmentResponseFromDepartment( departmentRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Department", id))));
        } catch (InvalidIdException e) {
            return new GenericResponse<>(new GenericError(1, "Invalid id", e.getMessage()));
        }
    }

    public Department post(DepartmentRequest deptRequest) throws InvalidIdException {
        BusinessUnit deptBu = businessUnitRepository.findById(deptRequest.getBusinessUnitId()).orElseThrow(()
                -> new InvalidIdException("Business Unit", deptRequest.getBusinessUnitId()));
        return departmentRepository.save(new Department(deptRequest.getDepartmentName(), deptBu));
    }

    public Department put(DepartmentRequest deptRequest, long id) throws InvalidIdException {
        Department department = departmentRepository.findById(id).orElseThrow(()
                    -> new InvalidIdException("Department", id));
        BusinessUnit deptBu = businessUnitRepository.findById(deptRequest.getBusinessUnitId()).orElseThrow(()
                -> new InvalidIdException("Business Unit", deptRequest.getBusinessUnitId()));
        department.setName(deptRequest.getDepartmentName());
        department.setBu(deptBu);
        return departmentRepository.save(department);
    }

    public Department patch(DepartmentRequest deptRequest, long id) throws InvalidIdException {
        Department department = departmentRepository.findById(id).orElseThrow(()
                -> new InvalidIdException("Department", id));
        if (deptRequest.getBusinessUnitId() > 0) {
            BusinessUnit deptBu = businessUnitRepository.findById(deptRequest.getBusinessUnitId()).orElseThrow(()
                    -> new InvalidIdException("Business Unit", deptRequest.getBusinessUnitId()));
            department.setBu(deptBu);
        }
        if (deptRequest.getDepartmentName() != null) {
            department.setName(deptRequest.getDepartmentName());
        }
        return departmentRepository.save(department);
    }
}
