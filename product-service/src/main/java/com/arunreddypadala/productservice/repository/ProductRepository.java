package com.arunreddypadala.productservice.repository;

import com.arunreddypadala.productservice.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {
}
