package com.roche.product.service.jpa.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
public class ProductJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Date created;
    @Column(nullable = false)
    private Boolean isDeleted;

    public ProductJpa() {
    }

    public ProductJpa(String name, Double price, Date created) {

        this.name = name;
        this.price = Math.round(price * 100);
        this.created = created;
    }

    private ProductJpa(Long id, String name, Long price, Date created, Boolean isDeleted) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.created = created;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductJpa setName(String name) {
        return new ProductJpa(this.id, name, this.price, this.created, this.isDeleted);
    }

    public Double getPrice() {
        return price / 100d;
    }

    public ProductJpa setPrice(Double price) {
        return new ProductJpa(this.id, this.name, Math.round(price * 100), this.created, this.isDeleted);
    }

    public Date getCreated() {
        return created;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public ProductJpa setDeleted(Boolean deleted) {
        return new ProductJpa(this.id, this.name, this.price, this.created, isDeleted);
    }
}
