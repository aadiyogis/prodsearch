package com.ecom.prodsearch.web.mapper;

import com.ecom.prodsearch.domain.Product;
import com.ecom.prodsearch.web.model.ProductDto;
import org.mapstruct.Mapper;

/**
 * aditya created on 09/02/20
 */
@Mapper
public interface ProductMapper {

    Product productDtoToProduct(ProductDto productDto);

    ProductDto productToProductDto(Product product);
}
