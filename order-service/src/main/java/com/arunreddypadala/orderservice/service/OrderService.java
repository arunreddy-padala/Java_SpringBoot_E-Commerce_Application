package com.arunreddypadala.orderservice.service;

import com.arunreddypadala.orderservice.dto.InventoryResponse;
import com.arunreddypadala.orderservice.dto.OrderLineItemsDto;
import com.arunreddypadala.orderservice.dto.OrderRequest;
import com.arunreddypadala.orderservice.model.Order;
import com.arunreddypadala.orderservice.model.OrderLineItems;
import com.arunreddypadala.orderservice.repository.OrderRepository;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class OrderService {

  private final OrderRepository orderRepository;

  private final WebClient.Builder webClientBuilder;

  public void placeOrder(OrderRequest orderRequest) {

      Order order = new Order();
      order.setOrderNumber(UUID.randomUUID().toString());

      List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList().
              stream().map(orderLineItemsDto -> mapToDto(orderLineItemsDto)).toList();

      order.setOrderLineItemsList(orderLineItemsList);

      List<String> skuCodes = order.getOrderLineItemsList()
              .stream()
              .map(orderLineItems -> orderLineItems.getSkuCode())
              .toList();

    InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
            .uri("http://inventory-service/api/inventory",
                    uriBuilder -> uriBuilder.queryParam("skuCode", skuCodes).build())
            .retrieve()
            .bodyToMono(InventoryResponse[].class)
            .block();

    boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse -> InventoryResponse.isInStock());

    if(allProductsInStock) {
      orderRepository.save(order);
    }
    else {
      throw new IllegalArgumentException("Product quantity is not in stock");
    }

  }

  private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {

    OrderLineItems orderLineItems = new OrderLineItems();
    orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
    orderLineItems.setPrice(orderLineItemsDto.getPrice());
    orderLineItems.setQuantity(orderLineItemsDto.getQuantity());

    return orderLineItems;


  }


}
