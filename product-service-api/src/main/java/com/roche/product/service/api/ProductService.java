package com.roche.product.service.api;

import com.roche.product.service.api.model.Product;

import java.util.Date;
import java.util.List;

public interface ProductService {

    default Product create(String name, Double price) {
        return create(name, price, new Date());
    }

    Product create(String name, Double price, Date created);

    Product get(Long id);

    Product update(Product product);

    Product delete(Product product);

    Product delete(Long id);

    List<Product> list();

}
