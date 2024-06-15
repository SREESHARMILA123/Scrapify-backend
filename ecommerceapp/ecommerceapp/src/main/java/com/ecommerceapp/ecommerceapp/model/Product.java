package com.ecommerceapp.ecommerceapp.model;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.annotation.Id;

@Document(collection = "products")
@Data
public class Product {
    @Id
    private String id;
    private String category;
    private String title;
    private String description;
    private double price;
    private String imgSrc;
    private String productTitle;
}
