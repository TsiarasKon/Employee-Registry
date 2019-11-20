package com.unisystems.registry.department;

import com.unisystems.registry.GenericResponse;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

public class DepartmentServiceShould {
    private DepartmentService service;
    DepartmentResponse departmentResponseFromMapper;

    @Mock
    private DepartmentRepository departmentRepository;
    @Mock
    private DepartmentMapper mapper;
    @Mock
    private MultipleDepartmentsResponse multipleDepartmentsResponse;

    private Iterable<Department> mockedDepartments = new ArrayList<Department>() {
        {
            add(new Department("department"));
            add(new Department("deptName"));
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        when(departmentRepository.findAll()).thenReturn(mockedDepartments);
        departmentResponseFromMapper = new DepartmentResponse(1, "name", "businnes unit");
        when(mapper.mapDepartmentResponseFromDepartment(any())).thenReturn(departmentResponseFromMapper);
        service = new DepartmentService(mapper, departmentRepository);
    }

    @Test
    public void retrieveDepartmentsFromRepository() {
        service.getAllDepartments().getData().getDepartmentResponse();
        Mockito.verify(departmentRepository).findAll();
    }

    @Test
    public void usesDepartmentMapper() {
        service.getAllDepartments().getData().getDepartmentResponse();
        Mockito.verify(mapper, times(1)).mapDepartmentResponseFromDepartment(any());
    }

    @Test
    public void returnsListOfGenericResponse() {
        GenericResponse<MultipleDepartmentsResponse> output = service.getAllDepartments();
        Assert.assertEquals(2, output.getData().getDepartmentResponse().size());
        GenericResponse<MultipleDepartmentsResponse> expected = new GenericResponse<>(multipleDepartmentsResponse);
        expected.getData().getDepartmentResponse().add(departmentResponseFromMapper);
        expected.getData().getDepartmentResponse().add(departmentResponseFromMapper);
        Assert.assertThat(output.getData().getDepartmentResponse(), CoreMatchers.hasItems(departmentResponseFromMapper,departmentResponseFromMapper));
    }
}
