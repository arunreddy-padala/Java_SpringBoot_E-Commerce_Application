package com.arunreddypadala.inventoryservice.service;


import com.arunreddypadala.inventoryservice.dto.InventoryResponse;
import com.arunreddypadala.inventoryservice.model.Inventory;
import com.arunreddypadala.inventoryservice.repository.InventoryRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class InventoryService {

    private final InventoryRepository inventoryRepository;

    @SneakyThrows
    public List<InventoryResponse> isInStock(List<String> skuCode)  {
        log.info("Wait Started");

      Thread.sleep(10000);

      log.info("Wait Ended");

         return inventoryRepository.findBySkuCodeIn(skuCode).stream()
                 .map(inventory ->
                           InventoryResponse.builder()
                                   .skuCode(inventory.getSkuCode())
                                   .isInStock(inventory.getQuantity()>0)
                                   .build()
                         ).toList();

    }

}
