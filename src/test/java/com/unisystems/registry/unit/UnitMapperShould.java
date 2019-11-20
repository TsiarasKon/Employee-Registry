package com.unisystems.registry.unit;


import com.unisystems.registry.department.Department;
import com.unisystems.registry.unit.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class UnitMapperShould {
    private UnitMapper mapper;
    private Unit unitInput;
    private Department departmentInput;
    private UnitResponse output;

    @Before
    public void setup(){
        mapper=new UnitMapper();
        Department dept=new Department("Sales Department");
        unitInput=new Unit(134,"unitName",dept);
        unitInput.setId(100);
        unitInput.setName("Different Name");
        unitInput.setDept(new Department("Other"));
        output=mapper.mapUnitResponseFromUnit(unitInput);
    }

    @Test
    public void keepSameId(){
        Assert.assertEquals(100,output.getId());
    }
    @Test
    public void keepSameName(){
        Assert.assertEquals("Different Name",output.getName());
    }
    @Test
    public void keepSameDepartment(){Assert.assertEquals(unitInput.getDept().getName(),output.getDepartmentName());}



}
