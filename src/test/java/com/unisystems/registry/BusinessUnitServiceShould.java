package com.unisystems.registry;

import com.unisystems.registry.business_unit.*;
import com.unisystems.registry.company.CompanyRepository;
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

    BusinessUnitResponse businessUnitResponse;

    @Mock
    private BusinessUnitRepository businessUnitRepository;

    @Mock
    BusinessUnitMapper mapper;

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
        businessUnitResponse = new BusinessUnitResponse(1,"bu", "co");
        when(mapper.mapBusinessUnitResponseFromBusinessUnit(any())).thenReturn(businessUnitResponse);
        service = new BusinessUnitService(mapper, businessUnitRepository);
    }

    @Test
    public void getBusinessUnitFromRepo()
    {
        service.getAllBusinessUnits();
        verify(businessUnitRepository).findAll();
    }

    @Test
    public void useOfTourMapper()
    {
        service.getAllBusinessUnits();
        verify(mapper,times(2)).mapBusinessUnitResponseFromBusinessUnit(any());
    }

}
