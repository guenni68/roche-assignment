package com.roche.product.service.jpa.repo;

import com.roche.product.service.jpa.entity.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<ProductJpa, Long> {
}
