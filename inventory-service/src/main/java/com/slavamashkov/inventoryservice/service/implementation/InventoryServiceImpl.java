package com.slavamashkov.inventoryservice.service.implementation;

import com.slavamashkov.inventoryservice.dto.InventoryResponse;
import com.slavamashkov.inventoryservice.model.Inventory;
import com.slavamashkov.inventoryservice.repository.InventoryRepository;
import com.slavamashkov.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public List<InventoryResponse> isInStock(List<String> skuCodes) {
        List<Inventory> inventories = inventoryRepository.findBySkuCodeIn(skuCodes);

        return inventories.stream()
                .map(inventory -> InventoryResponse.builder()
                    .skuCode(inventory.getSkuCode())
                    .isInStock(inventory.getQuantity() > 0)
                    .build()
                ).collect(Collectors.toList());
    }
}
