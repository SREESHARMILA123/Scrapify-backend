package com.ecommerceapp.ecommerceapp.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "cartItems")
public class CartItem {
    @Id
    private String id;
    private String title;
    private String description;
    private String imgSrc;
    private double price;
    private double quantity;

    public Object getProductId() {
        return id;
    }
}
