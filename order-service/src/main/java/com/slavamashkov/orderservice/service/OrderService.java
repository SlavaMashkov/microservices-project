package com.slavamashkov.orderservice.service;

import com.slavamashkov.orderservice.dto.OrderRequest;
import com.slavamashkov.orderservice.dto.OrderResponse;

public interface OrderService {
    // CREATE
    OrderResponse placeOrder(OrderRequest orderRequest);

    // READ

    // UPDATE

    // DELETE
    void deleteOrderById(Long id);
}
