package com.unisystems.registry.employee;

import com.unisystems.registry.employee.*;
import com.unisystems.registry.unit.Unit;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

public class EmployeeMapperShould {
    private EmployeeMapper mapper;
    private Employee employeeInput;
    private EmployeeResponse expectedOutput;

    @Before
    public void setUp(){
        mapper = new EmployeeMapper();
        employeeInput = new Employee("Kostas","Klagkos","Veikou 50, Galatsi","6978232451", LocalDate.of(2019, 11, 2), null, true, EmployeeContractType.INTERNAL, EmployeePosition.PROGRAMMER);
        employeeInput.setId(1);
        employeeInput.setRecordNum(111500001);
        employeeInput.setUnit(new Unit("5G"));
        expectedOutput = new EmployeeResponse(1,111500001,"Kostas Klagkos","6978232451","2019-11-02 - present","Company Internal","Programmer","active","5G");
    }

    @Test
    public void mapEmployeeFromEmployeeResponse(){
        EmployeeResponse output = mapper.mapEmployee(employeeInput);
        Assert.assertThat(expectedOutput, Matchers.samePropertyValuesAs(output));
    }
}
