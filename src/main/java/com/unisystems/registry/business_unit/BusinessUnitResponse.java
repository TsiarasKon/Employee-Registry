package com.unisystems.registry.business_unit;

public class BusinessUnitResponse
{
    private long id;
    private String businessUnitName;
    private String companyName;

    public BusinessUnitResponse(long id, String businessUnitName, String companyName) {
        this.id = id;
        this.businessUnitName = businessUnitName;
        this.companyName = companyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBusinessUnitName() {
        return businessUnitName;
    }

    public void setBusinessUnitName(String businessUnitName) {
        this.businessUnitName = businessUnitName;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
