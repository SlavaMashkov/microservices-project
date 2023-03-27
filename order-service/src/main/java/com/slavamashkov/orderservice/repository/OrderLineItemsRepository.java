package com.slavamashkov.orderservice.repository;

import com.slavamashkov.orderservice.model.OrderLineItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineItemsRepository extends JpaRepository<OrderLineItems, Long> {
}
