package com.unisystems.registry.tasks;

import com.unisystems.registry.RegistryApplication;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RegistryApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TaskFeature {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllTasks() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/tasks")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllTasksJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getTasksById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/getTaskById/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(TaskByIdJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEasyDifficulty() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/easy")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonEasy1));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getEasyDifficultyWithEmployeeNumber() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/easy/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonEasy2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMediumDifficulty() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/medium")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonMedium));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getMediumDifficultyWithEmployeeNumber() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/medium/2")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonMedium2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHardDifficulty() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/hard")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonHard));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getHardDifficultyWithEmployeeNumber() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/hard/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonHard2));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getDifficultyByEmployeeNumber() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/taskDifficulty/difficulty/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DifficultyJson.jsonEmployeeNumber));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
