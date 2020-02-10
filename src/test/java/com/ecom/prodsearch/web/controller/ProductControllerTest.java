package com.ecom.prodsearch.web.controller;

import com.ecom.prodsearch.service.IProducts;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ProductController.class)
@ComponentScan(basePackages = {"com.ecom.prodsearch.web.mapper"})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private IProducts products;

    @Test
    void getAll() throws Exception {
        when(products.getAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/v1/product/all").accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk());
    }
}
