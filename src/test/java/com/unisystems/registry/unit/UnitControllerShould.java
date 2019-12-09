package com.unisystems.registry.unit;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.company.CompanyController;
import com.unisystems.registry.company.CompanyResponse;
import com.unisystems.registry.department.DepartmentController;
import com.unisystems.registry.department.DepartmentResponse;
import com.unisystems.registry.department.DepartmentService;
import com.unisystems.registry.department.MultipleDepartmentsResponse;
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

public class UnitControllerShould {

    UnitController controller;

    @Mock
    UnitService service;

    @Mock
    UnitResponse unitResponse1;

    @Mock
    UnitResponse unitResponse2;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        List<UnitResponse> mockedUnits = new ArrayList<>();
        mockedUnits.add(unitResponse1);
        mockedUnits.add(unitResponse2);
        GenericResponse<MultipleUnitResponse> mockedResponse = new GenericResponse(new MultipleUnitResponse(mockedUnits));
        when(service.getAllUnits()).thenReturn(mockedResponse);
        GenericResponse<UnitResponse> mockedUnitById = new GenericResponse(unitResponse1);
        when(service.getUnitWithId(unitResponse1.getId())).thenReturn(mockedUnitById);
        controller = new UnitController(service);
    }


    //GetAllUnits
    @Test
    public void returnAllUnits() {
        ResponseEntity<MultipleUnitResponse> actual = controller.getAllUnits();

        Assert.assertThat(actual.getBody().getUnitsResponses(), CoreMatchers.hasItems(unitResponse1,unitResponse2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails() {
        GenericError error = mockServiceFailure();
        ResponseEntity<GenericError> actual =  controller.getAllUnits();
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailure() {
        GenericError error = new GenericError(0, "Error", "Something went wrong");
        when(service.getAllUnits()).thenReturn(new GenericResponse<>(error));
        controller = new UnitController(service);
        return error;
    }

    //GetUnitById
    @Test
    public void returnUnitWithGivenId() {
        ResponseEntity<UnitResponse> actual = controller.getUnitById(unitResponse1.getId());
        Assert.assertThat(actual.getBody().getName(), CoreMatchers.sameInstance(unitResponse1.getName()));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFailsForId() {
        GenericError error = mockServiceFailureForId();
        ResponseEntity<GenericError> actual = controller.getUnitById(unitResponse1.getId());
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailureForId() {
        GenericError error = new GenericError(1, "Error" ,"Something went wrong please try again");
        when(service.getUnitWithId(unitResponse1.getId())).thenReturn(new GenericResponse<>(error));
        controller = new UnitController(service);
        return error;
    }

}
