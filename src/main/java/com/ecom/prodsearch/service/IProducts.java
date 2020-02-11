package com.ecom.prodsearch.service;

import com.ecom.prodsearch.domain.Product;
import com.ecom.prodsearch.web.model.ProductDto;

import javax.validation.Valid;
import java.util.List;

/**
 * aditya created on 09/02/20
 */
public interface IProducts {

    List<Product> getAll();

    ProductDto save(@Valid ProductDto product);
}
