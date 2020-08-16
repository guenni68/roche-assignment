package com.roche.prodcut.service.web.dto;

import java.io.Serializable;
import java.util.Date;

public class ProductDto implements Serializable {

    private Long id;
    private String name;
    private Double price;
    private Date createdAt;

    public ProductDto() {
    }

    public ProductDto(Long id, String name, Double price, Date createdAt) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
    }
}
