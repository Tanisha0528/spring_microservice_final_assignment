package com.shop.management.service.shopManagementService.controller;


import com.shop.management.service.shopManagementService.ShopMangementServiceApplication;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ShopMangementServiceApplication.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class CategoryControllerTest {

    //for controller based mocks
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext categoryContext;//autowired the configuration

    @Before
    public void setup()
    {
        this.mockMvc= MockMvcBuilders.webAppContextSetup(categoryContext).build();
    }
    @Test
    public void verifyAllCategories() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$",hasSize(2)))
                .andDo(print());

        ;
    }
   @Test
    public void verifyValidCategory() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/category/abcdefgh")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.errorCode").value(400))
                .andExpect(jsonPath("$.message").value("request can't be placed due to malfunctioned syntax"));


    }



}
