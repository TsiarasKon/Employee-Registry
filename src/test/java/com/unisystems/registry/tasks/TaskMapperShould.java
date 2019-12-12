package com.unisystems.registry.tasks;

import com.unisystems.registry.task.TaskStatus;
import com.unisystems.registry.task.Task;
import com.unisystems.registry.task.TaskMapper;
import com.unisystems.registry.task.TaskResponse;
import org.hamcrest.Matchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class TaskMapperShould {
    private TaskMapper mapper;
    private Task taskInput;
    private TaskResponse expectedOutput;

    @Before
    public void setUp(){
        mapper = new TaskMapper();
        taskInput = new Task("Android App For New Product", "Create an android app for the new product.",3,2,2, TaskStatus.NEW,null);
        taskInput.setId(1);
        expectedOutput = new TaskResponse(1,"Android App For New Product", "Create an android app for the new product.","MEDIUM", "New");
    }

    @Test
    public void mapTaskFromTaskResponse(){
        TaskResponse output = mapper.mapTask(taskInput);
        Assert.assertThat(expectedOutput,Matchers.samePropertyValuesAs(output));
    }
}
