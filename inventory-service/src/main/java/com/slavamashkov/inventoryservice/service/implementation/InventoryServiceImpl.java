package com.slavamashkov.inventoryservice.service.implementation;

import com.slavamashkov.inventoryservice.model.Inventory;
import com.slavamashkov.inventoryservice.repository.InventoryRepository;
import com.slavamashkov.inventoryservice.service.InventoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class InventoryServiceImpl implements InventoryService {
    private final InventoryRepository inventoryRepository;

    @Transactional(readOnly = true)
    @Override
    public boolean isInStock(String skuCode) {
        Optional<Inventory> optionalInventory = inventoryRepository.findBySkuCode(skuCode);

        if (optionalInventory.isPresent()) {
            Inventory inventory = optionalInventory.get();

            return inventory.getQuantity() > 0;
        }

        return false;
    }
}
