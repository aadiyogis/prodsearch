package com.ecom.prodsearch.web.controller;

import com.ecom.prodsearch.domain.Product;
import com.ecom.prodsearch.service.IProducts;
import com.ecom.prodsearch.web.model.ProductDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * aditya created on 09/02/20
 */
@RestController
@RequestMapping("/api/v1/product")
@Slf4j
@RequiredArgsConstructor
public class ProductController {

    private final IProducts products;

    @GetMapping("/all")
    public ResponseEntity<List> getAll() {
        log.debug("Inside controller to get all products");
        return new ResponseEntity<>(products.getAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ProductDto> save(@RequestBody @Valid ProductDto productDto) {
        log.debug("saving product");
        ProductDto save = products.save(productDto);
        return new ResponseEntity<>(save, HttpStatus.CREATED);
    }

}
