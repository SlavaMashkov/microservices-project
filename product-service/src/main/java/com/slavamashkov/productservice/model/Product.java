package com.slavamashkov.productservice.model;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(value = "product")
public class Product {
    @Id
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
}
