package com.unisystems.registry.department;

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
public class DepartmentsFeature {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllDepartments() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/departments")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllDepartmentsJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getDepartmentById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/departments/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(DepartmentByIdJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void postDepartment()
    {
        try {
            DepartmentRequest request=new DepartmentRequest();
            request.setDepartmentName("Department");
            mockMvc.perform(MockMvcRequestBuilders.post("/departments")
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
    public void updateDepartment(){
        try {
            DepartmentRequest request=new DepartmentRequest();
            request.setDepartmentName("Department");
            mockMvc.perform(MockMvcRequestBuilders.put("/departments/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPatchDepartment(){
        try {
            DepartmentRequest request=new DepartmentRequest();
            request.setDepartmentName("Department");
            mockMvc.perform(MockMvcRequestBuilders.patch("/departments/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
