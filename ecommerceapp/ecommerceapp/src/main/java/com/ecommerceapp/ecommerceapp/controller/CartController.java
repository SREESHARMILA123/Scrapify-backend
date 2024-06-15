package com.ecommerceapp.ecommerceapp.controller;

import com.ecommerceapp.ecommerceapp.model.Cart;
import com.ecommerceapp.ecommerceapp.model.CartItem;
import com.ecommerceapp.ecommerceapp.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public ResponseEntity<Cart> getCart(@PathVariable String userId) {
        Optional<Cart> cart = cartService.findByUserId(userId);
        return cart.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
    @DeleteMapping("/{userId}")
    public ResponseEntity<Object> deleteCartById(@PathVariable String userId) {
        try {
            cartService.deleteById(userId);
            return ResponseEntity.ok("Cart deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to delete cart: " + e.getMessage());
        }
    }


    @PostMapping("/{userId}")
    public ResponseEntity<Cart> addToCart(@PathVariable String userId, @RequestBody CartItem cartItem) {
        Optional<Cart> cartOpt = cartService.findByUserId(userId);
        Cart cart;
        if (cartOpt.isPresent()) {
            cart = cartOpt.get();
            Optional<CartItem> existingItemOpt = cart.getItems().stream()
                    .filter(item -> item.getProductId().equals(cartItem.getProductId()))
                    .findFirst();
            if (existingItemOpt.isPresent()) {
                CartItem existingItem = existingItemOpt.get();
                existingItem.setQuantity(existingItem.getQuantity() + cartItem.getQuantity());
            } else {
                cart.getItems().add(cartItem);
            }
        } else {
            cart = new Cart();
            cart.setUserId(userId);
            cart.setItems(List.of(cartItem));
        }
        return ResponseEntity.ok(cartService.save(cart));
    }
}

