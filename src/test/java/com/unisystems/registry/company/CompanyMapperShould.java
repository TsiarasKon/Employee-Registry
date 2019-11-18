package com.unisystems.registry.company;


import com.unisystems.registry.company.Company;
import com.unisystems.registry.company.CompanyMapper;
import com.unisystems.registry.company.CompanyResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CompanyMapperShould {

    private CompanyMapper mapper;
    private Company companyInput;
    private CompanyResponse expectedOutput;

@Before
    public void setup(){

    mapper = new CompanyMapper();
    companyInput = new Company("Unisystem");
    companyInput.setId(4);
    expectedOutput = mapper.mapCompanyResponseFromCompany(companyInput);
}

    @Test
    public void keepId() {
        Assert.assertEquals(expectedOutput.getId(),companyInput.getId());
    }

    @Test
    public void keepName() {
        Assert.assertEquals(expectedOutput.getName(),companyInput.getName());
    }

}
