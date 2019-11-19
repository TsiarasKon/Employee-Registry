package com.unisystems.registry;

import com.unisystems.registry.business_unit.BusinessUnit;
import com.unisystems.registry.company.Company;
import com.unisystems.registry.department.Department;
import com.unisystems.registry.unit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

public class UnitMapperShould {
    private UnitMapper mapper;
    private Unit unitInput;
    private Department departmentInput;
    private UnitResponse output;

    @Before
    public void setup(){
        mapper=new UnitMapper();
        Department dept=new Department();
        unitInput=new Unit(134,"unitName",dept);
        unitInput.setId(100);
        unitInput.setName("ChangedName");
        unitInput.setDept(new Department());

        departmentInput=new Department();
        departmentInput.setId(200);
        unitInput.setDept(departmentInput);
        output=mapper.mapUnitResponseFromUnit(unitInput);
    }

    @Test
    public void keepSameId(){
        Assert.assertEquals(100,output.getId());
    }
    @Test
    public void keepSameName(){
        Assert.assertEquals("ChangedName",output.getName());
    }

    /*@Test
    public void keepSameDept(){
        Assert.assertEquals("dept_name",output.getDepartmentName());
    }*/

}
