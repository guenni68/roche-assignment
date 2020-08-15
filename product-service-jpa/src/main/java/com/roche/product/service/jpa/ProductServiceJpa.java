package com.roche.product.service.jpa;

import com.roche.product.service.api.ProductService;
import com.roche.product.service.api.model.Product;
import com.roche.product.service.jpa.entity.ProductJpa;
import com.roche.product.service.jpa.repo.ProductRepo;

import java.util.Date;
import java.util.List;

public class ProductServiceJpa implements ProductService {

    private final ProductRepo productRepo;

    public ProductServiceJpa(ProductRepo productRepo) {

        this.productRepo = productRepo;
    }

    @Override
    public Product create(String name, Double price, Date created) {

        ProductJpa productJpa = new ProductJpa(name, price, created);

        return null;
    }

    @Override
    public Product get(Long id) {
        return null;
    }

    @Override
    public Product update(Product product) {
        return null;
    }

    @Override
    public Product delete(Product product) {
        return null;
    }

    @Override
    public Product delete(Long id) {
        return null;
    }

    @Override
    public List<Product> list() {
        return null;
    }
}
