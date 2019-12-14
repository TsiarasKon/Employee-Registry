package com.unisystems.registry.employee;

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
            mockMvc.perform(MockMvcRequestBuilders.get("/employees")
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
            mockMvc.perform(MockMvcRequestBuilders.get("/employees-in/Unit/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllEmployeesByCriteriaJSon.json));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Test
    public void postEmployee()
    {
        try {
            EmployeeRequest request=new EmployeeRequest();
            request.setAddress("address");
            request.setFirstName("Petroula");
            request.setLastName("Stamouli");
            request.setPhoneNumber("210-6824119");
            request.setUnitId(3);
            request.setRecruitmentDate("12/6/2021");
            mockMvc.perform(MockMvcRequestBuilders.post("/employees")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isCreated());

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
    public void updateEmployee(){
        try {
            EmployeeRequest request=new EmployeeRequest();
            request.setAddress("address");
            request.setFirstName("Petroula");
            request.setLastName("Stamouli");
            request.setPhoneNumber("210-6824119");
            request.setUnitId(3);
            request.setRecruitmentDate("12/6/2021");
            mockMvc.perform(MockMvcRequestBuilders.put("/employees/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPatchEmployee(){
        try {
            EmployeeRequest request=new EmployeeRequest();
            request.setAddress("address");
            request.setFirstName("Petroula");
            request.setLastName("Stamouli");
            request.setPhoneNumber("210-6824119");
            request.setUnitId(3);
            request.setRecruitmentDate("12/6/2021");
            mockMvc.perform(MockMvcRequestBuilders.patch("/employees/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
