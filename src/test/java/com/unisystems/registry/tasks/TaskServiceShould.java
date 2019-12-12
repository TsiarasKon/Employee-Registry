package com.unisystems.registry.tasks;

import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.task.*;
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

public class TaskServiceShould {

    private TaskService service;
    TaskResponse taskResponseFromMapper;

    @Mock
    private TaskRepository taskRepository;
    @Mock
    private TaskMapper mapper;
    @Mock
    private MultipleTaskResponse multipleTaskResponse;

    private Iterable<Task> mockedTasks = new ArrayList<Task>(){
        {
            add(new Task("Service Test","Testing the TaskService",1,1,2, TaskStatus.NEW,null));
            add(new Task("Service Test","Testing the TaskController",1,2,2, TaskStatus.NEW,null));
        }
    };

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        when(taskRepository.findAll()).thenReturn(mockedTasks);
        taskResponseFromMapper = new TaskResponse(5,"CheckTask","A task just for test TaskService","easy",null);
        when(mapper.mapTask(any())).thenReturn(taskResponseFromMapper);
        service = new TaskService(mapper,taskRepository);
    }

    @Test
    public void retrieveTasksfromRepository(){
        service.getAllTasks();
        Mockito.verify(taskRepository).findAll();
    }

    @Test
    public void usesTaskMapper(){
        service.getAllTasks().getData().getTaskResponse();
        Mockito.verify(mapper,times(2)).mapTask(any());
    }

    @Test
    public void returnsListOfGenericResponse() {
        GenericResponse<MultipleTaskResponse> output = service.getAllTasks();
        Assert.assertEquals(2, output.getData().getTaskResponse().size());
        GenericResponse<MultipleTaskResponse> expected = new GenericResponse<>(multipleTaskResponse);
        expected.getData().getTaskResponse().add(taskResponseFromMapper);
        expected.getData().getTaskResponse().add(taskResponseFromMapper);
        Assert.assertThat(output.getData().getTaskResponse(), CoreMatchers.hasItems(taskResponseFromMapper,taskResponseFromMapper));
    }


}
