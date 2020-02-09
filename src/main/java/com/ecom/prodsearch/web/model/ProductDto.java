package com.ecom.prodsearch.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;
import java.math.BigDecimal;

/**
 * aditya created on 09/02/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {

    @NotBlank
    private String name;

    @NotBlank
    private String productType;

    @NotBlank
    private String brand;

    @Positive
    private BigDecimal price;

    @NotNull
    private String size;

    @PositiveOrZero
    private Integer quantity;
}
