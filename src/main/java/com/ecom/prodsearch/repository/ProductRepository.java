package com.ecom.prodsearch.repository;

import com.ecom.prodsearch.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/**
 * aditya created on 09/02/20
 */
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

}
