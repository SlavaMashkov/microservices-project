package com.slavamashkov.productservice.service;

import com.slavamashkov.productservice.dto.ProductRequest;
import com.slavamashkov.productservice.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    void createProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProducts();
}
