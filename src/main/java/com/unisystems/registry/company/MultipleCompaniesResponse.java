package com.unisystems.registry.company;

import java.util.List;

public class MultipleCompaniesResponse {

public List<CompanyResponse> companyResponses;

    public MultipleCompaniesResponse(List<CompanyResponse> companyResponses) {
        this.companyResponses = companyResponses;
    }

    public List<CompanyResponse> getCompanyResponses() {
        return companyResponses;
    }

    public void setCompanyResponses(List<CompanyResponse> companyResponses) {
        this.companyResponses = companyResponses;
    }
}
