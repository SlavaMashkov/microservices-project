package com.slavamashkov.inventoryservice.service;

import com.slavamashkov.inventoryservice.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {
    // CREATE

    // READ
    List<InventoryResponse> isInStock(List<String> skuCodes);

    // UPDATE

    // DELETE
}
