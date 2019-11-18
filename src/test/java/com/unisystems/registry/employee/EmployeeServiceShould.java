package com.unisystems.registry.employee;

import com.unisystems.registry.GenericResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;


import java.util.ArrayList;


import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class EmployeeServiceShould {
    private EmployeeService service;
    EmployeeResponse employeeResponseFromMapper;

    @Mock
    private EmployeeRepository employeeRepository;
    @Mock
    private EmployeeMapper mapper;
    @Mock
    private MultipleEmployeeResponse multipleEmployeeResponse;

    private Iterable<Employee> mockedEmployees = new ArrayList<Employee>() {
        {
            add(new Employee("Bruce","Willis","69846383", LocalDate.now(),EmployeeContractType.EXTERNAL,EmployeePosition.ACCOUNTANT));
        }
    };


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(employeeRepository.findAll()).thenReturn(mockedEmployees);
        employeeResponseFromMapper = new EmployeeResponse(134,54638,"Kim Kardashian","48736272","2019-5-6","external","sales","true","unitName");
        when(mapper.mapEmployee(any())).thenReturn(employeeResponseFromMapper);
        service = new EmployeeService(mapper, employeeRepository);
    }

    @Test
    public void retrieveEmployeesFromRepository() {
        service.getAllEmployees().getData().getEmployeeResponses();
        Mockito.verify(employeeRepository).findAll();
    }

    @Test
    public void usesEmployeeMapper() {
        service.getAllEmployees().getData().getEmployeeResponses();
        Mockito.verify(mapper, times(1)).mapEmployee(any());
    }

    @Test
    @Ignore
    public void returnsListOfGenericResponse() {
        GenericResponse<MultipleEmployeeResponse> output = service.getAllEmployees();
        Assert.assertEquals(2, output.getData().getEmployeeResponses().size());
        GenericResponse<MultipleEmployeeResponse> expected = new GenericResponse<>(multipleEmployeeResponse);
        expected.getData().getEmployeeResponses().add(employeeResponseFromMapper);
        expected.getData().getEmployeeResponses().add(employeeResponseFromMapper);
        Assert.assertThat(output.getData().getEmployeeResponses(), CoreMatchers.hasItems(employeeResponseFromMapper,employeeResponseFromMapper));
    }
}
