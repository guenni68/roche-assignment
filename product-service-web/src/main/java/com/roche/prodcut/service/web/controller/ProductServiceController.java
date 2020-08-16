package com.roche.prodcut.service.web.controller;

import com.roche.prodcut.service.web.dto.ProductDto;
import com.roche.product.service.api.ProductService;
import com.roche.product.service.api.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/product")
public class ProductServiceController {

    private final ProductService productService;

    public ProductServiceController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public List<ProductDto> listProducts() {
        return productService
                .list()
                .stream()
                .map(product -> mapProductToDto(product))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable("id") Long id) {

        Product product = productService.get(id);
        return mapProductToDto(product);
    }

    @PostMapping("/")
    public ProductDto create(@RequestBody ProductDto productDto) {

        Product product = productService.create(productDto.getName(), productDto.getPrice());

        return mapProductToDto(product);
    }

    @PutMapping("/")
    public ProductDto update(@RequestBody ProductDto productDto) {

        Product product = productService.update(mapProductDtoToProduct(productDto));

        return mapProductToDto(product);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) {

        productService.delete(id);
    }

    private Product mapProductDtoToProduct(final ProductDto productDto) {

        return new Product() {
            @Override
            public Long getId() {
                return productDto.getId();
            }

            @Override
            public String getName() {
                return productDto.getName();
            }

            @Override
            public Double getPrice() {
                return productDto.getPrice();
            }

            @Override
            public Date getCreatedAt() {
                return productDto.getCreatedAt();
            }
        };
    }


    private ProductDto mapProductToDto(Product product) {

        return new ProductDto(
                product.getId(),
                product.getName(),
                product.getPrice(),
                product.getCreatedAt());
    }

}
