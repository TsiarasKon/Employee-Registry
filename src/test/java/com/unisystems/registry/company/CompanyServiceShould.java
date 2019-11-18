package com.unisystems.registry.company;

import com.unisystems.registry.GenericResponse;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class CompanyServiceShould {
    private CompanyService service;
    CompanyResponse companyResponseFromMapper;

    @Mock
    private CompanyRepository companyRepository;
    @Mock
    private CompanyMapper mapper;
    @Mock
    private MultipleCompaniesResponse multipleCompaniesResponse;

    private Iterable<Company> mockedCompanies = new ArrayList<Company>() {
        {
            add(new Company("UniSystems"));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(companyRepository.findAll()).thenReturn(mockedCompanies);
        companyResponseFromMapper = new CompanyResponse("Company");
        when(mapper.mapCompanyResponseFromCompany(any())).thenReturn(companyResponseFromMapper);
        service = new CompanyService(mapper, companyRepository);
    }

    @Test
    public void retrieveCompaniesFromRepository() {
        service.getAllCompany().getData().getCompanyResponses();
        Mockito.verify(companyRepository).findAll();
    }

    @Test
    public void usesCompanyMapper() {
        service.getAllCompany().getData().getCompanyResponses();
        Mockito.verify(mapper, times(1)).mapCompanyResponseFromCompany(any());
    }

    @Test
    @Ignore
    public void returnsListOfGenericResponse() {
        GenericResponse<MultipleCompaniesResponse> output = service.getAllCompany();
        Assert.assertEquals(2, output.getData().getCompanyResponses().size());
        GenericResponse<MultipleCompaniesResponse> expected = new GenericResponse<>(multipleCompaniesResponse);
        expected.getData().getCompanyResponses().add(companyResponseFromMapper);
        expected.getData().getCompanyResponses().add(companyResponseFromMapper);
        Assert.assertThat(output.getData().getCompanyResponses(), CoreMatchers.hasItems(companyResponseFromMapper,companyResponseFromMapper));
    }
}
