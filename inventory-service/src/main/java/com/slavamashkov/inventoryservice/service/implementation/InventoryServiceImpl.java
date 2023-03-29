package com.slavamashkov.inventoryservice.service.implementation;

import com.slavamashkov.inventoryservice.dto.InventoryResponse;
import com.slavamashkov.inventoryservice.model.Inventory;
import com.slavamashkov.inventoryservice.repository.InventoryRepository;
import com.slavamashkov.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);

        List<InventoryResponse> inventoryResponses = inventories.stream()
                .map(inventory -> InventoryResponse.builder()
                        .skuCode(inventory.getSkuCode())
                        .isInStock(inventory.getQuantity() > 0)
                        .build()
                ).collect(Collectors.toList());

        log.info(inventoryResponses.toString());

        return inventoryResponses;
    }
}
