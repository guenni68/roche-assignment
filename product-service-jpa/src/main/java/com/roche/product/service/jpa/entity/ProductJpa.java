package com.roche.product.service.jpa.entity;

import com.roche.product.service.api.model.Product;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;

@Entity
public class ProductJpa implements Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private Long price;
    @Column(nullable = false)
    private Date createdAt;
    @Column(nullable = false)
    private Boolean isDeleted = false;

    public ProductJpa() {
    }

    public ProductJpa(String name, Double price, Date createdAt) {

        this.name = name;
        this.price = Math.round(price * 100);
        this.createdAt = createdAt;
    }

    private ProductJpa(Long id, String name, Long price, Date createdAt, Boolean isDeleted) {

        this.id = id;
        this.name = name;
        this.price = price;
        this.createdAt = createdAt;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ProductJpa setName(String name) {
        return new ProductJpa(this.id, name, this.price, this.createdAt, this.isDeleted);
    }

    public Double getPrice() {
        return price / 100d;
    }

    public ProductJpa setPrice(Double price) {
        return new ProductJpa(this.id, this.name, Math.round(price * 100), this.createdAt, this.isDeleted);
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }

    public ProductJpa setDeleted(Boolean deleted) {
        return new ProductJpa(this.id, this.name, this.price, this.createdAt, isDeleted);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductJpa that = (ProductJpa) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(name, that.name) &&
                Objects.equals(price, that.price) &&
                Objects.equals(createdAt, that.createdAt) &&
                Objects.equals(isDeleted, that.isDeleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, createdAt, isDeleted);
    }
}
