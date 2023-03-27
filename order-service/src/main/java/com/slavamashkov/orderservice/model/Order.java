package com.slavamashkov.orderservice.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String orderNumber;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLineItems> orderLineItems;
}
