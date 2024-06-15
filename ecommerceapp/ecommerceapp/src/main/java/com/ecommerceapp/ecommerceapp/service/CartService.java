package com.ecommerceapp.ecommerceapp.service;
import com.ecommerceapp.ecommerceapp.model.Cart;
import com.ecommerceapp.ecommerceapp.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class CartService {

    @Autowired
    private CartRepository cartRepository;

    public Cart save(Cart cart) {
        return cartRepository.save(cart);
    }

    public void deleteById(String userId){
        cartRepository.deleteByUserId(userId);
    }


    public Optional<Cart> findByUserId(String userId) {
        return cartRepository.findByUserId(userId);
    }

//    public Optional<Cart> findAll(String userId) {
//    }
}
