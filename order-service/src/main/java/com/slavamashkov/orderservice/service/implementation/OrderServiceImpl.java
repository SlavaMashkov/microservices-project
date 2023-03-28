package com.slavamashkov.orderservice.service.implementation;

import com.slavamashkov.orderservice.dto.InventoryRequest;
import com.slavamashkov.orderservice.dto.InventoryResponse;
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
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

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
    private final WebClient webClient;

    @Override
    public void placeOrder(OrderRequest orderRequest) {
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

        List<OrderLineItems> orderLineItems = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(OrderLineItemsDto::mapFromDto)
                .collect(Collectors.toList());

        order.setOrderLineItems(orderLineItems);

        List<String> skuCodes = order.getOrderLineItems().stream()
                .map(OrderLineItems::getSkuCode)
                .toList();

        InventoryRequest request = InventoryRequest.builder()
                .skuCodes(skuCodes)
                .build();

        List<InventoryResponse> inventoryResponses = webClient.post()
                .uri("http://localhost:8082/api/inventory")
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .body(BodyInserters.fromValue(request))
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<InventoryResponse>>() {})
                .block();

        boolean allProductsInStock = inventoryResponses.stream().allMatch(InventoryResponse::isInStock);

        if (allProductsInStock) {
            orderRepository.save(order);
            for (OrderLineItems oli : orderLineItems) {
                oli.setOrder(order);
                orderLineItemsRepository.save(oli);
            }

            log.info("Order with id: {} is saved", order.getId());
        } else {
            throw new IllegalArgumentException("Product is not in stock, please try again later");
        }
    }

    @Override
    public void deleteOrderById(Long id) {
        Order order = orderRepository.findById(id).orElseThrow(EntityNotFoundException::new);

        orderRepository.delete(order);
    }
}
