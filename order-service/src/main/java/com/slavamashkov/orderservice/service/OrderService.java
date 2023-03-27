package com.slavamashkov.orderservice.service;

import com.slavamashkov.orderservice.dto.OrderRequest;

public interface OrderService {
    // CREATE
    void placeOrder(OrderRequest orderRequest);

    // READ

    // UPDATE

    // DELETE
    void deleteOrderById(Long id);
}
