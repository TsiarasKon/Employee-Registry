package com.unisystems.registry.company;

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
public class CompaniesFeature {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllCompanies() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/companies")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllCompaniesJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getCompanyById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/companies/1")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(CompanyByIdJSon.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void postCompany()
    {
        try {
            CompanyRequest request=new CompanyRequest();
            request.setCompanyName("Company");
            mockMvc.perform(MockMvcRequestBuilders.post("/companies")
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
    public void updateCompany(){
        try {
            CompanyRequest request=new CompanyRequest();
            request.setCompanyName("Company");
            mockMvc.perform(MockMvcRequestBuilders.put("/companies/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checkPatchBusinessUnit(){
        try {
            CompanyRequest request=new CompanyRequest();
            request.setCompanyName("Company");
            mockMvc.perform(MockMvcRequestBuilders.patch("/companies/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
