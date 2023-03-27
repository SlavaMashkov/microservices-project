package com.slavamashkov.productservice.repository;

import com.slavamashkov.productservice.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

}
