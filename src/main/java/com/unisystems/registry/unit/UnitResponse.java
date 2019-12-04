package com.unisystems.registry.unit;

public class UnitResponse {
    private  long id;
    private String name;
    private String departmentName;

    public UnitResponse(long id, String name,String departmentName) {
        this.id = id;
        this.name = name;
        this.departmentName = departmentName;

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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }
}
