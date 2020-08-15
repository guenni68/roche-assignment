package com.roche.product.service.jpa;

import com.roche.product.service.api.ProductService;
import com.roche.product.service.api.model.Product;
import com.roche.product.service.jpa.entity.ProductJpa;
import com.roche.product.service.jpa.repo.ProductRepo;
import org.springframework.transaction.annotation.Transactional;

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
        productRepo.save(productJpa);

        return productJpa;
    }

    @Override
    @Transactional
    public Product get(Long id) {

        Product product = productRepo.findOneByIdAndIsDeletedFalse(id);

        if (product == null) {
            throw new RuntimeException("Product not found");
        }

        return product;
    }

    @Override
    public Product update(Product product) {

        ProductJpa productJpa = productRepo.findOneByIdAndIsDeletedFalse(product.getId());

        if (productJpa == null) {
            throw new RuntimeException("Product not found");
        }

        ProductJpa productJpa1 = productJpa
                .setName(product.getName())
                .setPrice(product.getPrice());

        return productRepo.save(productJpa1);
    }

    @Override
    public void delete(Product product) {
        delete(product.getId());
    }

    @Override
    public void delete(Long id) {
        ProductJpa productJpa = productRepo.getOne(id);
        productRepo.save(productJpa.setDeleted(true));
    }

    @Override
    public List<? extends Product> list() {
        return productRepo.findAllByIsDeletedFalse();
    }
}
