package com.unisystems.registry.company;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.business_unit.BusinessUnitController;
import com.unisystems.registry.business_unit.BusinessUnitResponse;
import com.unisystems.registry.business_unit.BusinessUnitService;
import com.unisystems.registry.business_unit.MultipleBusinessUnitResponse;
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

public class CompanyControllerShould {

    CompanyController controller;

    @Mock
    CompanyService service;

    @Mock
    CompanyResponse companyResponse1;

    @Mock
    CompanyResponse companyResponse2;


    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        List<CompanyResponse> mockedCompanies = new ArrayList<>();
        mockedCompanies.add(companyResponse1);
        mockedCompanies.add(companyResponse2);
        GenericResponse<MultipleCompaniesResponse> mockedResponse = new GenericResponse(new MultipleCompaniesResponse(mockedCompanies));
        GenericResponse<CompanyResponse> mockedCompanyById = new GenericResponse(companyResponse1);
        when(service.getAllCompany()).thenReturn(mockedResponse);
        when(service.getCompanyWithId(companyResponse1.getId())).thenReturn(mockedCompanyById);
        controller = new CompanyController(service);
    }


    //GetAllCompanies
    @Test
    public void returnAllCompanies() {
        ResponseEntity<MultipleCompaniesResponse> actual = controller.getAllCompanies();

        Assert.assertThat(actual.getBody().getCompanyResponses(), CoreMatchers.hasItems(companyResponse1,companyResponse2));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFails() {
        GenericError error = mockServiceFailure();
        ResponseEntity<MultipleCompaniesResponse> actual =  controller.getAllCompanies();
        Assert.assertEquals(error, actual.getBody());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailure() {
        GenericError error = new GenericError(0, "Error", "Something went wrong");
        when(service.getAllCompany()).thenReturn(new GenericResponse<>(error));
        controller = new CompanyController(service);
        return error;
    }

    //GetCompanyById
    @Test
    public void returnCompanyWithGivenId() {
        ResponseEntity<CompanyResponse> actual = controller.getCompanyById(companyResponse1.getId());
        Assert.assertThat(actual.getBody().getName(), CoreMatchers.sameInstance(companyResponse1.getName()));
        Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
    }

    @Test
    public void returnsErrorWhenServiceFailsForId() {
        GenericError error = mockServiceFailureForId();
        ResponseEntity<GenericError> actual = controller.getCompanyById(companyResponse1.getId());
        Assert.assertEquals(error.getCode(), actual.getBody().getCode());
        Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
    }

    private GenericError mockServiceFailureForId() {
        GenericError error = new GenericError(1, "Error" ,"Something went wrong please try again");
        when(service.getCompanyWithId(companyResponse1.getId())).thenReturn(new GenericResponse<>(error));
        controller = new CompanyController(service);
        return error;
    }

}
