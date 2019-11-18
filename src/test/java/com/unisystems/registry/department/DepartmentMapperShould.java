package com.unisystems.registry.department;

import com.unisystems.registry.business_unit.BusinessUnit;
import com.unisystems.registry.department.Department;
import com.unisystems.registry.department.DepartmentMapper;
import com.unisystems.registry.department.DepartmentResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class DepartmentMapperShould {

    private DepartmentMapper mapper;
    private Department departmentInput;
    private DepartmentResponse expectedOutput;

    @Before
    public void setup() {
        mapper = new DepartmentMapper();
        departmentInput = new Department("Testing Department");
        departmentInput.setId(1);
        departmentInput.setBu(new BusinessUnit("BU for this Department"));
        expectedOutput = new DepartmentResponse(1,"Testing Department","BU for this Department");
    }

    @Test
    public void mapDepartmentResponseFromDepartment() {
        DepartmentResponse output = mapper.mapDepartmentResponseFromDepartment(departmentInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
