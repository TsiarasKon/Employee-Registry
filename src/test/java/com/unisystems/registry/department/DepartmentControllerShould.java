package com.unisystems.registry.department;

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

public class DepartmentControllerShould {

    DepartmentController controller;

    @Mock
    DepartmentService service;

    @Mock
    DepartmentResponse departmentResponse1;

    @Mock
    DepartmentResponse departmentResponse2;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        List<DepartmentResponse> mockedDepartments = new ArrayList<>();
        mockedDepartments.add(departmentResponse1);
        mockedDepartments.add(departmentResponse2);
        GenericResponse<MultipleDepartmentsResponse> mockedResponse = new GenericResponse(new MultipleDepartmentsResponse(mockedDepartments));
        GenericResponse<DepartmentResponse> mockedDepartmentById = new GenericResponse(departmentResponse1);
        when(service.getAllDepartments()).thenReturn(mockedResponse);
        when(service.getDepartmentWithId(departmentResponse1.getId())).thenReturn(mockedDepartmentById);
        controller = new DepartmentController(service);
    }

    //GetAllDepartments
    @Test
    public void returnAllDepartments() {
        ResponseEntity<MultipleDepartmentsResponse> actual = controller.getAllDepartments();

        Assert.assertThat(actual.getBody().getDepartmentResponse(), CoreMatchers.hasItems(departmentResponse1,departmentResponse2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails() {
        GenericError error = mockServiceFailure();
        ResponseEntity<MultipleDepartmentsResponse> actual =  controller.getAllDepartments();
        Assert.assertEquals(error, actual.getBody());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailure() {
        GenericError error = new GenericError(0, "Error", "Something went wrong");
        when(service.getAllDepartments()).thenReturn(new GenericResponse<>(error));
        controller = new DepartmentController(service);
        return error;
    }

    //GetDepartmentById
    @Test
    public void returnDepartmentWithGivenId() {
        ResponseEntity<DepartmentResponse> actual = controller.getDepartmentWithId(departmentResponse1.getId());
        Assert.assertThat(actual.getBody().getDepartmentName(), CoreMatchers.sameInstance(departmentResponse1.getDepartmentName()));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFailsForId() {
        GenericError error = mockServiceFailureForId();
        ResponseEntity<GenericError> actual = controller.getDepartmentWithId(departmentResponse1.getId());
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailureForId() {
        GenericError error = new GenericError(1, "Error" ,"Something went wrong please try again");
        when(service.getDepartmentWithId(departmentResponse1.getId())).thenReturn(new GenericResponse<>(error));
        controller = new DepartmentController(service);
        return error;
    }
}
