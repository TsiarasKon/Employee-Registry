package com.unisystems.registry.tasks;

import com.unisystems.registry.GenericError;
import com.unisystems.registry.GenericResponse;
import com.unisystems.registry.task.*;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.when;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
public class TaskControllerShould {

        TaskController controller;
        @Mock
        TaskService service;
        @Mock
        TaskResponse taskResponse1;
        @Mock
        TaskResponse taskResponse2;

        @Mock
        TaskResponseId taskResponseId1;

        @Mock
        TaskResponseId taskResponseId2;

        @Before
        public void setup(){
            MockitoAnnotations.initMocks(this);
            List<TaskResponse> mockedTasks = new ArrayList<>();
            List<TaskResponseId> taskResponseList = new ArrayList<>();

            mockedTasks.add(taskResponse1);
            mockedTasks.add(taskResponse2);

            taskResponseList.add(taskResponseId1);
            taskResponseList.add(taskResponseId2);

            GenericResponse<MultipleTaskResponse> mockedResponse = new GenericResponse(new MultipleTaskResponse(mockedTasks));
            GenericResponse<MultipleTaskResponseId> mockedResponseId = new GenericResponse(taskResponseId1);

            GenericResponse<MultipleTaskResponseId> mockedTaskResponseId = new GenericResponse(new MultipleTaskResponseId(taskResponseList));

            when(service.getAllTasks()).thenReturn(mockedResponse);

            when(service.getTaskById(taskResponseId1.getId())).thenReturn(mockedResponseId);
            when(service.getTasksByDifficulty("easy",null)).thenReturn(mockedTaskResponseId);
            when(service.getTasksByDifficulty("easy", (long) 1)).thenReturn(mockedTaskResponseId);
            when(service.getTasksByDifficulty(null, (long) 1)).thenReturn(mockedTaskResponseId);
            controller = new TaskController(service);
        }

        //Unit test for getAllTasks
        @Test
        public void returnAllEmployees() {
            ResponseEntity<MultipleTaskResponse> actual = controller.getAllTasks();

            Assert.assertThat(actual.getBody().getTaskResponse(), CoreMatchers.hasItems(taskResponse1, taskResponse2));
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }

        @Test
        public void returnsErrorWhenServiceFails() {
            GenericError error = mockServiceFailure();
            ResponseEntity<GenericError> actual = controller.getAllTasks();
            Assert.assertEquals(error.getCode(), actual.getBody().getCode());
            Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        }

        private GenericError mockServiceFailure() {
            GenericError error = new GenericError(1, "Error", "Something went wrong");
            when(service.getAllTasks()).thenReturn(new GenericResponse<>(error));
            controller = new TaskController(service);
            return error;
        }

        //Unit test for getTaskById
        @Test
        public void returnsTaskByGivenId(){
            ResponseEntity<TaskResponseId> actual=controller.getTaskById(taskResponseId1.getId());
            Assert.assertThat(actual.getBody().getId(),CoreMatchers.sameInstance(taskResponseId1.getId()));
            Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());

        }

        @Test
        public void returnsErrorWhenServiceFailsForId() {
            GenericError error = mockServiceFailureForId();
            ResponseEntity<GenericError> actual = controller.getTaskById(taskResponseId1.getId());
            Assert.assertEquals(error.getCode(), actual.getBody().getCode());
            Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        }

        private GenericError mockServiceFailureForId() {
            GenericError error = new GenericError(1, "Error", "Something went wrong");
            when(service.getTaskById(taskResponseId1.getId())).thenReturn(new GenericResponse<>(error));
            controller = new TaskController(service);
            return error;
        }


        //Unit test for getTaskByDifficulty (only difficulty) case
        @Test
        public void returnsTaskByGivenDifficulty(){
            ResponseEntity<MultipleTaskResponseId> actual = controller.getTasksInCriteria("easy");
            Assert.assertThat(actual.getBody().getTaskResponse(), CoreMatchers.hasItems(taskResponseId1,taskResponseId2));
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }

        @Test
        public void returnsErrorWhenServiceFailsForCriteria() {
            GenericError error = mockServiceFailureForCriteria();
            ResponseEntity<GenericError> actual = controller.getTasksInCriteria("easy");
            Assert.assertEquals(error.getCode(), actual.getBody().getCode());
            Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        }

        private GenericError mockServiceFailureForCriteria() {
            GenericError error = new GenericError(1, "Error", "Something went wrong");
            when(service.getTasksByDifficulty("easy", null)).thenReturn(new GenericResponse<>(error));
            controller = new TaskController(service);
            return error;
        }

        //Unit test for getTaskByDifficulty (difficulty, NumberOfEmployees) case
        @Test
        public void returnsTaskByGivenDifficultyAndNumber(){
            ResponseEntity<MultipleTaskResponseId> actual = controller.getTasksInCriteria("easy",1);
            Assert.assertThat(actual.getBody().getTaskResponse(), CoreMatchers.hasItems(taskResponseId1));
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }

        @Test
        public void returnsErrorWhenServiceFailsForCriterias() {
            GenericError error = mockServiceFailureForCriterias();
            ResponseEntity<GenericError> actual = controller.getTasksInCriteria("easy",1);
            Assert.assertEquals(error.getCode(), actual.getBody().getCode());
            Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        }

        private GenericError mockServiceFailureForCriterias() {
            GenericError error = new GenericError(1, "Error", "Something went wrong");
            when(service.getTasksByDifficulty("easy", (long) 1)).thenReturn(new GenericResponse<>(error));
            controller = new TaskController(service);
            return error;
        }

        //Unit test for getTaskByDifficulty (only NumberOfEmployees) case
        @Test
        public void returnsTaskByGivenEmployeesNumber(){
            ResponseEntity<MultipleTaskResponseId> actual = controller.getTasksInCriteria(null,1);
            Assert.assertThat(actual.getBody().getTaskResponse(), CoreMatchers.hasItems(taskResponseId1));
            Assert.assertEquals(HttpStatus.OK, actual.getStatusCode());
        }

        @Test
        public void returnsErrorWhenServiceFailsForEmployeesNumber() {
            GenericError error = mockServiceFailureForEmployeesNumber();
            ResponseEntity<GenericError> actual = controller.getTasksInCriteria(null,1);
            Assert.assertEquals(error.getCode(), actual.getBody().getCode());
            Assert.assertEquals(HttpStatus.BAD_REQUEST, actual.getStatusCode());
        }

        private GenericError mockServiceFailureForEmployeesNumber() {
            GenericError error = new GenericError(1, "Error", "Something went wrong");
            when(service.getTasksByDifficulty(null, (long) 1)).thenReturn(new GenericResponse<>(error));
            controller = new TaskController(service);
            return error;
        }
}