package com.slavamashkov.orderservice.dto;

import com.slavamashkov.orderservice.model.Order;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {
    private String orderNumber;
    private List<OrderLineItemsDto> orderLineItems;

    public static OrderResponse mapToOrderResponse(Order order) {
        return OrderResponse.builder()
                .orderNumber(order.getOrderNumber())
                .orderLineItems(
                        order.getOrderLineItems().stream()
                                .map(OrderLineItemsDto::mapToDto)
                                .collect(Collectors.toList())
                )
                .build();
    }
}
