package com.arunreddypadala.inventoryservice.service;


import com.arunreddypadala.inventoryservice.dto.InventoryResponse;
import com.arunreddypadala.inventoryservice.model.Inventory;
import com.arunreddypadala.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    public List<InventoryResponse> isInStock(List<String> skuCode) {

         return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                 .map(inventory ->
                           InventoryResponse.builder()
                                   .skuCode(inventory.getSkuCode())
                                   .isInStock(inventory.getQuantity()>0)
                                   .build()
                         ).toList();

    }

}
