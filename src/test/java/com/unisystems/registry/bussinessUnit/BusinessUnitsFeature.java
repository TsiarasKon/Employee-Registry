package com.unisystems.registry.bussinessUnit;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisystems.registry.RegistryApplication;
import com.unisystems.registry.business_unit.BusinessUnitRequest;
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
public class BusinessUnitsFeature {
    MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void getAllBusinessUnits() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/business-units")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(AllBusinessUnitsJson.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void getAllBusinessUnitById() {
        try {
            mockMvc.perform(MockMvcRequestBuilders.get("/business-units/2")
                    .contentType(MediaType.APPLICATION_JSON)
            )
                    .andExpect(status().isOk())
                    .andExpect(content().json(BusinessUnitByIdJSon.json));
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Test
    public void postBusinessUnit()
    {
        try {
            BusinessUnitRequest request=new BusinessUnitRequest();
            request.setBusinessUnitName("BusinessUnit");
            request.setCompanyId(1);
            mockMvc.perform(MockMvcRequestBuilders.post("/business-units")
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
    public void updateBusinessUnit(){
        try {
            BusinessUnitRequest request=new BusinessUnitRequest();
            request.setBusinessUnitName("BusinessUnit");
            request.setCompanyId(1);
            mockMvc.perform(MockMvcRequestBuilders.put("/business-units/1")
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
            BusinessUnitRequest request=new BusinessUnitRequest();
            request.setBusinessUnitName("BusinessUnit");
            request.setCompanyId(1);
            mockMvc.perform(MockMvcRequestBuilders.patch("/business-units/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(request))
            )
                    .andExpect(status().isOk());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
