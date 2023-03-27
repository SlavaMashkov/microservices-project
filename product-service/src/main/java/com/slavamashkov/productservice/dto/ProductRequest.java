package com.slavamashkov.productservice.dto;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
}
