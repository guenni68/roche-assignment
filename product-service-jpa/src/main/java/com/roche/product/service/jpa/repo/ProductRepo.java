package com.roche.product.service.jpa.repo;

import com.roche.product.service.jpa.entity.ProductJpa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<ProductJpa, Long> {

    ProductJpa findOneByIdAndIsDeletedFalse(Long id);

    List<ProductJpa> findAllByIsDeletedFalse();
}
