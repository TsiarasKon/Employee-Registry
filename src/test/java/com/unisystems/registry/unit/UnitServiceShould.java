package com.unisystems.registry.unit;

import com.unisystems.registry.department.Department;
import com.unisystems.registry.department.DepartmentRepository;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayDeque;
import java.util.ArrayList;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class UnitServiceShould {

    private UnitService service;
    UnitResponse unitResponseFromMapper;

    @Mock
    private UnitRepository unitRepository;
    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private UnitMapper mapper;

    private Iterable<Unit> mockedUnits=new ArrayDeque<Unit>(){
        {
            add(new Unit(1,"Unit",new Department("Sales Department")));
        }
    };

    public UnitServiceShould() {
    }

    @Before
    public  void setup(){

        MockitoAnnotations.initMocks(this);
        when(unitRepository.findAll()).thenReturn(mockedUnits);
        unitResponseFromMapper = new UnitResponse(1,"Unit Name",new Department("dept name"));
        //List<UnitResponse> unitResponses = Arrays.asList(unitResponseFromMapper);
        when(mapper.mapUnitResponseFromUnit(any())).thenReturn(unitResponseFromMapper);
        service = new UnitService(mapper, unitRepository, departmentRepository);
        //MultipleUnitResponse multipleUnitResponseFromMapper = new MultipleUnitResponse(unitResponses);
    }

    @Test
    public void retrieveUnitsFromRepository(){
        service.getAllUnits();
        Mockito.verify(unitRepository).findAll();
    }

    @Test
    public void usesUnitMapper(){
        service.getAllUnits();
        Mockito.verify(mapper,times(2)).mapUnitResponseFromUnit(any());
    }

    @Test
    @Ignore
    public void returnsUnitOfUnitResponse(){
        List<UnitResponse> output= (List<UnitResponse>) service.getAllUnits();
        Assert.assertEquals(2,output.size());
        List<UnitResponse> expectedList=new ArrayList<>();
        expectedList.add(unitResponseFromMapper);
        expectedList.add(unitResponseFromMapper);
        Assert.assertThat(output, CoreMatchers.hasItems(unitResponseFromMapper,unitResponseFromMapper));
    }



}
