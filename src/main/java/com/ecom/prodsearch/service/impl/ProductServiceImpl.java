package com.ecom.prodsearch.service.impl;

import com.ecom.prodsearch.domain.Product;
import com.ecom.prodsearch.repository.ProductRepository;
import com.ecom.prodsearch.service.IProducts;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * aditya created on 09/02/20
 */
@Service
@Slf4j
public class ProductServiceImpl implements IProducts {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getAll() {
        log.debug("Inside service method");
        return productRepository.findAll();
    }
}
