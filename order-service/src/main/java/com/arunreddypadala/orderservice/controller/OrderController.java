package com.arunreddypadala.orderservice.controller;


import com.arunreddypadala.orderservice.dto.OrderRequest;
import com.arunreddypadala.orderservice.service.OrderService;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.timelimiter.annotation.TimeLimiter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/order")
public class OrderController {

  private final OrderService orderService;

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
  @TimeLimiter(name = "inventory")
  @Retry(name = "inventory")
  public CompletableFuture<String> placeOrder(@RequestBody OrderRequest orderRequest) {
    return CompletableFuture.supplyAsync(() -> orderService.placeOrder(orderRequest));

  }

  public CompletableFuture<String> fallbackMethod(OrderRequest orderRequest, RuntimeException runtimeException) {

  return CompletableFuture.supplyAsync(() -> "Oops! Something went wrong, please try again after sometime");

  }



}
