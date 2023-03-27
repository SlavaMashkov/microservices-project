package com.slavamashkov.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_order_line_items")
public class OrderLineItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private BigDecimal price;
    private String skuCode;
    private Integer quantity;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
