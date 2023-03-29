package com.slavamashkov.orderservice.service;

import com.slavamashkov.orderservice.dto.OrderRequest;

public interface OrderService {
    // CREATE
    String placeOrder(OrderRequest orderRequest);

    // READ

    // UPDATE

    // DELETE
    void deleteOrderById(Long id);
}
