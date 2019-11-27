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
import static org.mockito.Mockito.when;

public class BusinessControllerShould {

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
        when(service.getAllBusinessUnits()).thenReturn(mockedResponse);
        controller = new BusinessUnitController(service);
    }

    @Test
    public void returnAllBusinessUnits() {
        ResponseEntity<MultipleBusinessUnitResponse> actual = controller.getAllBusinessUnits();

        Assert.assertThat(actual.getBody().getBusinessUnitResponse(), CoreMatchers.hasItems(businessUnitResponse1,businessUnitResponse2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails() {
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

}
