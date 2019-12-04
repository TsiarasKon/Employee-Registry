package com.unisystems.registry.company;

public class CompanyResponse {

    public long id;
    public String companyName;

    public CompanyResponse(long id, String companyName) {
        this.id = id;
        this.companyName = companyName;
    }

    public CompanyResponse(String companyName) {
        this.companyName = companyName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
}
