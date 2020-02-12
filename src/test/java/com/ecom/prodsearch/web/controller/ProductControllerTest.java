package com.ecom.prodsearch.web.controller;

import com.ecom.prodsearch.domain.Product;
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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.restdocs.constraints.ConstraintDescriptions;
import org.springframework.restdocs.payload.FieldDescriptor;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.mockito.Mockito.when;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.requestParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
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
        ResponseEntity<ProductDto> productDtoResponseEntity = new ResponseEntity<>(shirt, HttpStatus.CREATED);
        ConstrainedFields fields = new ConstrainedFields(ProductDto.class);

        mockMvc.perform(post("/api/v1/product")
                .accept(MediaType.APPLICATION_JSON)
                .content(shirtProd)
                .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isCreated())
//                .andExpect(content().string(shirtProd))
                .andDo(document("v1/product",
                        requestFields(
                                fields.withPath("name").description("name of product"),
                                fields.withPath("brand").description("brand of product"),
                                fields.withPath("productType").description("product type"),
                                fields.withPath("price").description("price of product"),
                                fields.withPath("size").description("size of product"),
                                fields.withPath("quantity").ignored()
//                        ),
//                        responseFields(
//                                fields.withPath("name").description("name of product"),
//                                fields.withPath("brand").description("brand of product"),
//                                fields.withPath("productType").description("product type"),
//                                fields.withPath("price").description("price of product"),
//                                fields.withPath("size").description("size of product"),
//                                fields.withPath("quantity").description("quantity of product created")
                        )
                ));
    }

    private static class ConstrainedFields {
        private final ConstraintDescriptions constraintDescriptions;

        public ConstrainedFields(Class<?> input) {
            this.constraintDescriptions = new ConstraintDescriptions(input);
        }

        private FieldDescriptor withPath(String path) {
            return fieldWithPath(path).attributes(key("constraints")
                    .value(StringUtils.collectionToDelimitedString(this.constraintDescriptions.descriptionsForProperty(path), ". ")));
        }
    }
}
