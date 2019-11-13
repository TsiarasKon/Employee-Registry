package com.unisystems.registry.department;

import java.util.List;

public class MultipleDepartmentsResponse {

    private List<DepartmentResponse> departmentResponse;

    public MultipleDepartmentsResponse(List<DepartmentResponse> departmentResponse) {
        this.departmentResponse = departmentResponse;
    }

    public List<DepartmentResponse> getDepartmentResponse() {
        return departmentResponse;
    }

    public void setDepartmentResponse(List<DepartmentResponse> departmentResponse) {
        this.departmentResponse = departmentResponse;
    }
}
