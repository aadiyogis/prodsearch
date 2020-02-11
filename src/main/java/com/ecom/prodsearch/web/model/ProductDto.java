package com.ecom.prodsearch.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;
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

    @Null
    @PositiveOrZero
    private Integer quantity;
}
