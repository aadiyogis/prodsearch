package com.ecom.prodsearch.service.impl;

import com.ecom.prodsearch.domain.Product;
import com.ecom.prodsearch.repository.ProductRepository;
import com.ecom.prodsearch.service.IProducts;
import com.ecom.prodsearch.web.mapper.ProductMapper;
import com.ecom.prodsearch.web.model.ProductDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * aditya created on 09/02/20
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProducts {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ProductMapper mapper;

    @Override
    public List<Product> getAll() {
        log.debug("Inside service method");
        return productRepository.findAll();
    }

    @Override
    public ProductDto save(@Valid ProductDto product) {
        Product mappedProduct = mapper.productDtoToProduct(product);
        mappedProduct.setCreatedDate(new Timestamp(new Date().getTime()));
        mappedProduct.setLastModifiedDate(new Timestamp(new Date().getTime()));
        mappedProduct.setQuantity(10);
        Product savedProduct = productRepository.save(mappedProduct);
        return mapper.productToProductDto(savedProduct);
    }
}
