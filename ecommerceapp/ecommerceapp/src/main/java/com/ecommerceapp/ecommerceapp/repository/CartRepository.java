package com.ecommerceapp.ecommerceapp.repository;
import com.ecommerceapp.ecommerceapp.model.Cart;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.Optional;

public interface CartRepository extends MongoRepository<Cart, String> {
    Optional<Cart> findByUserId(String userId);
    void deleteByUserId(String userId);
}

