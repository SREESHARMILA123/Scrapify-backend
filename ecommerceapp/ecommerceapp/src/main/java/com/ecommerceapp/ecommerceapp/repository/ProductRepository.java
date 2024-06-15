package com.ecommerceapp.ecommerceapp.repository;
import com.ecommerceapp.ecommerceapp.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

public interface ProductRepository extends MongoRepository<Product, String> {
}