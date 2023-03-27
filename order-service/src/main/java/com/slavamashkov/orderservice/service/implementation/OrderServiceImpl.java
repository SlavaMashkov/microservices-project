package com.slavamashkov.orderservice.service.implementation;

import com.slavamashkov.orderservice.dto.OrderLineItemsDto;
import com.slavamashkov.orderservice.dto.OrderRequest;
import com.slavamashkov.orderservice.model.Order;
import com.slavamashkov.orderservice.model.OrderLineItems;
import com.slavamashkov.orderservice.repository.OrderLineItemsRepository;
import com.slavamashkov.orderservice.repository.OrderRepository;
import com.slavamashkov.orderservice.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderLineItemsRepository orderLineItemsRepository;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(OrderLineItemsDto::mapFromDto)
                .collect(Collectors.toList());

        order.setOrderLineItems(orderLineItems);

        orderRepository.save(order);
        for (OrderLineItems oli : orderLineItems) {
            oli.setOrder(order);
            orderLineItemsRepository.save(oli);
        }

        log.info("Product with id: {} is saved", order.getId());
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        orderRepository.delete(order);
    }
}
