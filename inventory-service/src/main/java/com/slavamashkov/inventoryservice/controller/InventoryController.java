package com.slavamashkov.inventoryservice.controller;

import com.slavamashkov.inventoryservice.dto.InventoryRequest;
import com.slavamashkov.inventoryservice.dto.InventoryResponse;
import com.slavamashkov.inventoryservice.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/inventory")
public class InventoryController {
    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> isInStock(@RequestBody InventoryRequest inventoryRequest) {
        return inventoryService.isInStock(inventoryRequest.getSkuCodes());
    }
}
