package com.unisystems.registry.bussinessUnit;


import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.business_unit.*;
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
import java.util.Optional;

import static org.mockito.Mockito.when;

public class BusinessUnitControllerShould {

    BusinessUnitController controller;

    @Mock
    BusinessUnitService service;

    @Mock
    BusinessUnitResponse businessUnitResponse1;

    @Mock
    BusinessUnitResponse businessUnitResponse2;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        List<BusinessUnitResponse> mockedBusinessUnits = new ArrayList<>();
        mockedBusinessUnits.add(businessUnitResponse1);
        mockedBusinessUnits.add(businessUnitResponse2);
        GenericResponse<MultipleBusinessUnitResponse> mockedResponse = new GenericResponse(new MultipleBusinessUnitResponse(mockedBusinessUnits));
        GenericResponse<BusinessUnitResponse> mockedBUById = new GenericResponse(businessUnitResponse1);
        when(service.getAllBusinessUnits()).thenReturn(mockedResponse);
        when(service.getBusinessUnitWithId(businessUnitResponse1.getId())).thenReturn(mockedBUById);
        controller = new BusinessUnitController(service);
    }


    //GetAllBusinessUnits
    @Test
    public void returnAllBusinessUnits() {
        ResponseEntity<MultipleBusinessUnitResponse> actual = controller.getAllBusinessUnits();

        Assert.assertThat(actual.getBody().getBusinessUnitResponse(), CoreMatchers.hasItems(businessUnitResponse1,businessUnitResponse2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorServiceFails() {
        GenericError error = mockServiceFailure();
        ResponseEntity<MultipleBusinessUnitResponse> actual =  controller.getAllBusinessUnits();
        Assert.assertEquals(error, actual.getBody());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailure() {
        GenericError error = new GenericError(0, "Error" ,"Something went wrong please try again");
        when(service.getAllBusinessUnits()).thenReturn(new GenericResponse<>(error));
        controller = new BusinessUnitController(service);
        return error;
    }


    //GetBusinessUnitById
    @Test
    public void returnBusinessWithGivenId() {
        ResponseEntity<BusinessUnitResponse> actual = controller.getBusinessUnitById(businessUnitResponse1.getId());
        Assert.assertThat(actual.getBody().getBusinessUnitName(), CoreMatchers.sameInstance(businessUnitResponse1.getBusinessUnitName()));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFailsForId() {
        GenericError error = mockServiceFailureForId();
        ResponseEntity<GenericError> actual = controller.getBusinessUnitById(businessUnitResponse1.getId());
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailureForId() {
        GenericError error = new GenericError(1, "Error" ,"Something went wrong please try again");
        when(service.getBusinessUnitWithId(businessUnitResponse1.getId())).thenReturn(new GenericResponse<>(error));
        controller = new BusinessUnitController(service);
        return error;
    }

}
