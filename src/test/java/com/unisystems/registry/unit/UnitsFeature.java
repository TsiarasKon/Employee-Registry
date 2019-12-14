package com.unisystems.registry.unit;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.unisystems.registry.InvalidIdException;
import com.unisystems.registry.RegistryApplication;
import com.unisystems.registry.department.Department;
import com.unisystems.registry.employee.AllEmployeesJson;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.web.JsonProjectingMethodInterceptorFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


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

    /*@Test
    public void postUnitById() throws Exception {
        try {
            UnitRequest unitRequest=new UnitRequest();
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




        //MvcResult result = mockMvc.perform(MockMvcRequestBuilders
        //      .post("/units")
        //    .accept(MediaType.APPLICATION_JSON).content(PostUnitByIdJSon.json)
        //  .contentType(MediaType.APPLICATION_JSON)).andReturn();

        //MockHttpServletResponse response = result.getResponse();
        //  Assert.assertEquals(HttpStatus.CREATED.value(), result.getResponse().getContentAsString());
    }*/
    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*@Test
    public void updateUnit()
    {
        try {
            UnitRequest testUnit=new UnitRequest();
            //Unit unit=repository.findById(Long.valueOf(1)).orElse(null);
            testUnit.setUnitName("6G");
            testUnit.setDepartmentId(1);
            //UnitRequest unitRequest = new UnitRequest("6G", 1);
            mockMvc.perform(MockMvcRequestBuilders
                    .put("/units/1")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(asJsonString(testUnit))
            )
                    //.andExpect(MockMvcResultMatchers.status().isOk());
                    .andExpect(content().json(UpdatedUnitByIdJson.json));
        }
        catch (Exception e) {
            e.printStackTrace();
        }*/
    }
   /* public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }*/


   /*@Test
           public  void checkPatch() throws Exception {
       ObjectMapper objectMapper=new ObjectMapper();
       mockMvc.perform(patch("/units/1")
               .contentType(MediaType.APPLICATION_JSON_VALUE)
               .content(objectMapper.writeValueAsString(
                       new UnitRequest("6G", 1)))
       ).andExpect(status().isOk());
   }*/
    //}



