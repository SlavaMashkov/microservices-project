package com.slavamashkov.inventoryservice.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InventoryResponse {
    private String skuCode;
    private boolean isInStock;
}
