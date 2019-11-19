package com.unisystems.registry;

import com.unisystems.registry.business_unit.BusinessUnit;
import com.unisystems.registry.business_unit.BusinessUnitMapper;
import com.unisystems.registry.business_unit.BusinessUnitResponse;
import com.unisystems.registry.company.Company;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BusinessUnitMapperShould
{
    private BusinessUnitMapper mapper;
    private BusinessUnit businessUnitInput;
    private BusinessUnitResponse expectedOutput;

    @Before
    public void setUp()
    {
        mapper = new BusinessUnitMapper();
        businessUnitInput = new BusinessUnit("Testing BusinessUnit");
        businessUnitInput.setId(1);
        businessUnitInput.setCompany(new Company("Test Company"));
        expectedOutput = new BusinessUnitResponse(1,"Testing BusinessUnit", "Test Company");
    }

    @Test
    public void mapBusinessUnitResponseFromBusinessUnit()
    {
        BusinessUnitResponse output = mapper.mapBusinessUnitResponseFromBusinessUnit(businessUnitInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }

    @Test
    public void keepSameId()
    {
        Assert.assertEquals(1, expectedOutput.getId());
    }

    @Test
    public void haveSameName()
    {
        Assert.assertEquals(businessUnitInput.getName(), expectedOutput.getBusinessUnitName());
    }

    @Test
    public void haveSameCompanyName()
    {
        Assert.assertEquals(businessUnitInput.getCompany().getName(), expectedOutput.getCompanyName());
    }
}
