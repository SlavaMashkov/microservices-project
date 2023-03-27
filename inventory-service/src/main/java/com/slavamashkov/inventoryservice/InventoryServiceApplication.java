package com.slavamashkov.inventoryservice;

import com.slavamashkov.inventoryservice.model.Inventory;
import com.slavamashkov.inventoryservice.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class InventoryServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(InventoryServiceApplication.class, args);
    }

    @Bean
    public CommandLineRunner loadData(InventoryRepository inventoryRepository) {
        return args -> {
            Inventory inventory_1 = new Inventory();
            inventory_1.setSkuCode("iphone_13");
            inventory_1.setQuantity(100);

            Inventory inventory_2 = new Inventory();
            inventory_2.setSkuCode("iphone_13_red");
            inventory_2.setQuantity(0);

            inventoryRepository.save(inventory_1);
            inventoryRepository.save(inventory_2);
        };
    }
}
