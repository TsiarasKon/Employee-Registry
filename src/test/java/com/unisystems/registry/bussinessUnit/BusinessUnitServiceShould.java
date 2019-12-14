package com.unisystems.registry.bussinessUnit;

import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.business_unit.*;
import com.unisystems.registry.company.CompanyRepository;
import com.unisystems.registry.company.MultipleCompaniesResponse;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BusinessUnitServiceShould
{
    private BusinessUnitService service;

    BusinessUnitResponse businessUnitResponseFromMapper;

    @Mock
    private BusinessUnitRepository businessUnitRepository;

    @Mock
    private BusinessUnitMapper mapper;
    @Mock
    private MultipleBusinessUnitResponse multipleBusinessUnitResponse;

    private Iterable<BusinessUnit> mockedBu = new ArrayList<BusinessUnit>()
    {
        {
            add(new BusinessUnit("FirstBusinessUnit"));
            add(new BusinessUnit("SecondBusinessUnit"));
        }
    };

    @Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
        when(businessUnitRepository.findAll()).thenReturn(mockedBu);
        businessUnitResponseFromMapper = new BusinessUnitResponse(1,"bu", "co");
        when(mapper.mapBusinessUnitResponseFromBusinessUnit(any())).thenReturn(businessUnitResponseFromMapper);
        service = new BusinessUnitService(mapper, businessUnitRepository);
    }

    @Test
    public void retrieveBusinessUnitsFromRepository()
    {
        service.getAllBusinessUnits();
        verify(businessUnitRepository).findAll();
    }

    @Test
    public void useOfBusinessUnitMapper()
    {
        service.getAllBusinessUnits();
        verify(mapper,times(2)).mapBusinessUnitResponseFromBusinessUnit(any());
    }

    @Test
    public void returnsListOfGenericResponse() {
        GenericResponse<MultipleBusinessUnitResponse> output = service.getAllBusinessUnits();
        Assert.assertEquals(2, output.getData().getBusinessUnitResponse().size());
        GenericResponse<MultipleBusinessUnitResponse> expected = new GenericResponse<>(multipleBusinessUnitResponse);
        expected.getData().getBusinessUnitResponse().add(businessUnitResponseFromMapper);
        expected.getData().getBusinessUnitResponse().add(businessUnitResponseFromMapper);
        Assert.assertThat(output.getData().getBusinessUnitResponse(), CoreMatchers.hasItems(businessUnitResponseFromMapper,businessUnitResponseFromMapper));
    }
}
