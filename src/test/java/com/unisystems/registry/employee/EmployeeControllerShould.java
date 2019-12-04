package com.unisystems.registry.employee;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.company.CompanyResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;


import static org.mockito.Mockito.when;



public class EmployeeControllerShould {

    EmployeeController controller;
    @Mock
    EmployeeService service;
    @Mock
    EmployeeResponse employee1;
    @Mock
    EmployeeResponse employee2;
    @Mock
    CompanyResponse company;


    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        List<EmployeeResponse> mockedEmployees = new ArrayList<EmployeeResponse>();

        mockedEmployees.add(employee1);
        mockedEmployees.add(employee2);

        GenericResponse<MultipleEmployeeResponse> mockedResponse = new GenericResponse(new MultipleEmployeeResponse(mockedEmployees));
        GenericResponse<EmployeeResponse> mockedResponseId=new GenericResponse(employee1);
        when(service.getAllEmployees()).thenReturn(mockedResponse);
        when(service.getEmployeeWithId(employee1.getId())).thenReturn(mockedResponseId);
        when(service.getEmployeesInCriteria("Company",company.getId())).thenReturn(mockedResponse);
        controller = new EmployeeController(service);

    }


    //Unit test for getAllEmployees
    @Test
    public void returnAllEmployees() {
        ResponseEntity<MultipleEmployeeResponse> actual = controller.getAllEmployees();

        Assert.assertThat(actual.getBody().getEmployeeResponses(), CoreMatchers.hasItems(employee1, employee2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails() {
        GenericError error = mockServiceFailure();
        ResponseEntity<GenericError> actual = controller.getAllEmployees();
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailure() {
        GenericError error = new GenericError(1, "Error", "Something went wrong");
        when(service.getAllEmployees()).thenReturn(new GenericResponse<>(error));
        controller = new EmployeeController(service);
        return error;
    }
    //Unit test for getEmployeeWithId

    @Test
    public void returnsEmployeeWithGivenId(){
        ResponseEntity<EmployeeResponse> actual=controller.getEmployeeWithId(employee1.getId());
        Assert.assertThat(actual.getBody().getId(),CoreMatchers.sameInstance(employee1.getId()));
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());

    }
    @Test
    public void returnsErrorWhenServiceFailsForId() {
        GenericError error = mockServiceFailureForId();
        ResponseEntity<GenericError> actual = controller.getEmployeeWithId(employee1.getId());
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailureForId() {
        GenericError error = new GenericError(1, "Error", "Something went wrong");
        when(service.getEmployeeWithId(employee1.getId())).thenReturn(new GenericResponse<>(error));
        controller = new EmployeeController(service);
        return error;
    }
    //Unit test for getEmployeesInCriteria
    @Test
    public void returnsEmployeeWithGivenCriteria(){
        ResponseEntity<MultipleEmployeeResponse> actual=controller.getEmployeesInCriteria("Company",company.getId());
        Assert.assertThat(actual.getBody().getEmployeeResponses(), CoreMatchers.hasItems(employee1, employee2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());

    }
    @Test
    public void returnsErrorWhenServiceFailsForCriteria() {
        GenericError error = mockServiceFailureForCriteria();
        ResponseEntity<GenericError> actual = controller.getEmployeesInCriteria("Company",company.getId());
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailureForCriteria() {
        GenericError error = new GenericError(1, "Error", "Something went wrong");
        when(service.getEmployeesInCriteria("Company",company.getId())).thenReturn(new GenericResponse<>(error));
        controller = new EmployeeController(service);
        return error;
    }
}
