package com.unisystems.registry.department;

public class DepartmentResponse {

    private long id;
    private String Department_Name;
    private String Business_Unit_Name;

    public DepartmentResponse(long id, String department_Name, String business_Unit_Name) {
        this.id = id;
        Department_Name = department_Name;
        Business_Unit_Name = business_Unit_Name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartment_Name() {
        return Department_Name;
    }

    public void setDepartment_Name(String department_Name) {
        Department_Name = department_Name;
    }

    public String getBusiness_Unit_Name() {
        return Business_Unit_Name;
    }

    public void setBusiness_Unit_Name(String business_Unit_Name) {
        Business_Unit_Name = business_Unit_Name;
    }
}
