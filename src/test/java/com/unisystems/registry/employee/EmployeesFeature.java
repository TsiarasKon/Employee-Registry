package com.unisystems.registry.employee;

import com.unisystems.registry.RegistryApplication;
import com.unisystems.registry.unit.AllUnitsJson;
import com.unisystems.registry.unit.UnitByIdJson;
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
public class EmployeesFeature {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllEmployees() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/allEmployees")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllEmployeesJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getEmployeeById(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/employees/2")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(EmployeeByIdJSon.json));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void getEmployeesByCriteria(){
        try{
            mockMvc.perform(MockMvcRequestBuilders.get("/employeesIn/Unit/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllEmployeesByCriteriaJSon.json));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
