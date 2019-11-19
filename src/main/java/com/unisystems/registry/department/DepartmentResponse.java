package com.unisystems.registry.department;

public class DepartmentResponse {

    private long id;
    private String departmentName;
    private String businessUnitName;

    public DepartmentResponse(long id, String department_Name, String business_Unit_Name) {
        this.id = id;
        departmentName = department_Name;
        businessUnitName = business_Unit_Name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }
}
