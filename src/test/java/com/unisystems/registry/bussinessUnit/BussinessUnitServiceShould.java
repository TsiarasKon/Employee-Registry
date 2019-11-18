package com.unisystems.registry.bussinessUnit;

import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.business_unit.*;
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

public class BussinessUnitServiceShould {

    private BusinessUnitService service;
    BusinessUnitResponse businessUnitResponseFromMapper;

    @Mock
    private BusinessUnitRepository businessUnitRepository;
    @Mock
    private BusinessUnitMapper mapper;
    @Mock
    private MultipleBusinessUnitResponse multipleBusinessUnitResponse;

    private Iterable<BusinessUnit> mockedBusinessUnits = new ArrayList<BusinessUnit>() {
        {
            add(new BusinessUnit("businessUnit"));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(businessUnitRepository.findAll()).thenReturn(mockedBusinessUnits);
        businessUnitResponseFromMapper = new BusinessUnitResponse(1, "name", "uni");
        when(mapper.mapBusinessUnitResponseFromBusinessUnit(any())).thenReturn(businessUnitResponseFromMapper);
        service = new BusinessUnitService(mapper, businessUnitRepository);
    }

    @Test
    public void retrieveBusinessUnitsFromRepository() {
        service.getAllBusinessUnits().getData().getBusinessUnitResponse();
        Mockito.verify(businessUnitRepository).findAll();
    }

    @Test
    public void usesBusinessUnitMapper() {
        service.getAllBusinessUnits().getData().getBusinessUnitResponse();
        Mockito.verify(mapper, times(1)).mapBusinessUnitResponseFromBusinessUnit(any());
    }

    @Test
    @Ignore
    public void returnsListOfGenericResponse() {
        GenericResponse<MultipleBusinessUnitResponse> output = service.getAllBusinessUnits();
        Assert.assertEquals(2, output.getData().getBusinessUnitResponse().size());
        GenericResponse<MultipleBusinessUnitResponse> expected = new GenericResponse<>(multipleBusinessUnitResponse);
        expected.getData().getBusinessUnitResponse().add(businessUnitResponseFromMapper);
        expected.getData().getBusinessUnitResponse().add(businessUnitResponseFromMapper);
        Assert.assertThat(output.getData().getBusinessUnitResponse(), CoreMatchers.hasItems(businessUnitResponseFromMapper,businessUnitResponseFromMapper));
    }
}
