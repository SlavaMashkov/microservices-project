package com.slavamashkov.productservice.dto;

import com.slavamashkov.productservice.model.Product;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponse {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;

    public static ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}
