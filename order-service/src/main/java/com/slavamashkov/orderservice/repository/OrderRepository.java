package com.slavamashkov.orderservice.repository;

import com.slavamashkov.orderservice.model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
