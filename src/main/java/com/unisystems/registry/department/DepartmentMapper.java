package com.unisystems.registry.department;

import org.springframework.stereotype.Component;

@Component
public class DepartmentMapper {

    public DepartmentResponse mapDepartmentResponseFromDepartment(Department department) {
        return new DepartmentResponse(
                department.getId(),
                department.getName(),
                department.getBu().getName()
        );
    }
}
