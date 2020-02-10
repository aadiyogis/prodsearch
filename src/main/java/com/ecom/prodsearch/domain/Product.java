package com.ecom.prodsearch.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * aditya created on 09/02/20
 */
@Data
@Slf4j
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Product {

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "PRODUCT_TYPE", nullable = false)
    private String productType;

    @Column(name = "BRAND", nullable = false)
    private String brand;

    @Column(name = "PRICE", nullable = false)
    private BigDecimal price;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "QUANTITY")
    private Integer quantity;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_id_sequence")
    @SequenceGenerator(name = "product_id_sequence", allocationSize = 100)
    private Long id;

    @Column(name = "CREATED_DATE")
    private Timestamp createdDate;

    @Column(name = "LAST_MODIFIED_DATE")
    private Timestamp lastModifiedDate;


}
