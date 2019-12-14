package com.unisystems.registry.unit;


import com.fasterxml.jackson.databind.ObjectMapper;

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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;


import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;



@RunWith(SpringRunner.class)
@SpringBootTest(classes = {RegistryApplication.class},
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UnitsFeature {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }


    @Test
    public void getAllUnits() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/units")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllUnitsJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getUnitById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/units/2")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(UnitByIdJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void postUnitById() throws Exception {
        try {
            UnitRequest unitRequest = new UnitRequest();
            unitRequest.setUnitName("6G");
            unitRequest.setDepartmentId(1);
            mockMvc.perform(MockMvcRequestBuilders.post("/units")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(unitRequest))
            )

                    .andExpect(MockMvcResultMatchers.status().isCreated());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void updateUnit(){
        try {
            UnitRequest unitRequest = new UnitRequest();
            unitRequest.setUnitName("6G");
            unitRequest.setDepartmentId(1);
            mockMvc.perform(MockMvcRequestBuilders.put("/units/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(unitRequest))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPatchUnit(){
        try {
            UnitRequest unitRequest = new UnitRequest();
            unitRequest.setUnitName("6G");
            unitRequest.setDepartmentId(1);
            mockMvc.perform(MockMvcRequestBuilders.patch("/units/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(unitRequest))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





