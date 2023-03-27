package com.slavamashkov.productservice.service.implementation;

import com.slavamashkov.productservice.dto.ProductRequest;
import com.slavamashkov.productservice.dto.ProductResponse;
import com.slavamashkov.productservice.model.Product;
import com.slavamashkov.productservice.repository.ProductRepository;
import com.slavamashkov.productservice.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    @Override
    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();

        productRepository.save(product);

        log.info("Product with id: {} is saved", product.getId());
    }

    @Override
    public List<ProductResponse> getAllProducts() {
        return productRepository.findAll()
                .stream()
                .map(ProductResponse::mapToProductResponse)
                .toList();
    }
}

