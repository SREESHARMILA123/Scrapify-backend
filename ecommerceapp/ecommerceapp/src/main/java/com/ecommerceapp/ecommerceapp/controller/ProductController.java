package com.ecommerceapp.ecommerceapp.controller;

import com.ecommerceapp.ecommerceapp.model.Product;
import com.ecommerceapp.ecommerceapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductService productService;
    //getting all products
    @GetMapping("/get")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.findAll();
        return ResponseEntity.ok(products);
    }
    //getting specified id products
    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        Optional<Product> product = productService.findById(id);
        return product.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    //creating new product
    @PostMapping("/add")
    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
        Product newProduct = productService.save(product);
        return ResponseEntity.ok(newProduct);
    }
    //for deleting specified products



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") String id) {
        Optional<Product> product = productService.findById(id);
        if (product.isPresent()) {
            productService.deleteById(id);
            return ResponseEntity.ok("Product Deleted successfully");
        } else {
            return ResponseEntity.notFound().build();
        }
    }

}
