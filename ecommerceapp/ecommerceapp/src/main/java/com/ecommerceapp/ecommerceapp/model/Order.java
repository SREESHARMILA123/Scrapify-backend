package com.ecommerceapp.ecommerceapp.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "orders")
public class Order {
    @Id
    private String id;
    private String category;
    private String title;
    private String imgSrc; // If you have user info
    private String description;
    private String price;

    public void setStatus(String orderPlaced) {
    }

    // Getters and Setters
}

