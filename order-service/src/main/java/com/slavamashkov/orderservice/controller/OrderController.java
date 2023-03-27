package com.slavamashkov.orderservice.controller;

import com.slavamashkov.orderservice.dto.OrderRequest;
import com.slavamashkov.orderservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void placeOrder(@RequestBody OrderRequest orderRequest) {
        orderService.placeOrder(orderRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable(name = "id") Long id) {
        orderService.deleteOrderById(id);
    }
}
