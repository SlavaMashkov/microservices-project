package com.slavamashkov.productservice.service;

import com.slavamashkov.productservice.dto.ProductRequest;
import com.slavamashkov.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    // CREATE
    ProductResponse createProduct(ProductRequest productRequest);

    // READ
    List<ProductResponse> getAllProducts();

    // UPDATE

    // DELETE
}
