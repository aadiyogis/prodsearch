package com.ecom.prodsearch.web.controller;

import com.ecom.prodsearch.service.IProducts;
import com.ecom.prodsearch.web.model.ProductDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
@WebMvcTest(ProductController.class)
@ComponentScan(basePackages = {"com.ecom.prodsearch.web.mapper"})
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    ObjectMapper mapper;

    @MockBean
    private IProducts products;

    ProductDto createProductDto() {
        return ProductDto.builder().brand("Louis Phillipe")
                .name("Shirt")
                .price(new BigDecimal(1232.23))
                .productType("Cloth")
                .size("S")
                .build();
    }

    @Test
    void getAll() throws Exception {
        when(products.getAll()).thenReturn(new ArrayList<>());
        mockMvc.perform(get("/api/v1/product/all").
                param("isCold", "yes")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(document("v1/product/all",
                        requestParameters(
                                parameterWithName("isCold").description("Do you need cold beer?")
                        )
                ));
    }

    @Test
    void saveProduct() throws Exception {
        ProductDto shirt = createProductDto();
        String shirtProd = mapper.writeValueAsString(shirt);
        mockMvc.perform(post("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .content(shirtProd)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
                .andDo(document("v1/product", requestFields(
                        fieldWithPath("name").description("name of product"),
                        fieldWithPath("brand").description("brand of product"),
                        fieldWithPath("productType").description("product type"),
                        fieldWithPath("price").description("price of product"),
                        fieldWithPath("size").description("size of product"),
                        fieldWithPath("quantity").description("quantity present")
                )));
    }
}
