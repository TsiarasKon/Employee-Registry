package com.unisystems.registry.unit;

import com.unisystems.registry.department.Department;

public class UnitResponse {
    private  long id;
    private String name;
    private Department department;

    public UnitResponse(long id, String name,Department department) {
        this.id = id;
        this.name = name;
        this.department=department;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
