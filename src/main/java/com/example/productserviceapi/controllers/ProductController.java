package com.example.productserviceapi.controllers;

import com.example.productserviceapi.exceptions.ProductLimitExceedException;
import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.services.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) throws ProductLimitExceedException {
        if(id<=0){
            throw new RuntimeException("Product id cannot be less than 0");
        }
        if(id>=30){
            throw new ProductLimitExceedException("Product limit exceeded");
        }

        return productService.getPRoductById(id);
    }

    @GetMapping
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }

    @PostMapping
    public ResponseEntity<Product> createProduct(@RequestBody Product prod) {
        return new ResponseEntity<>(productService.createProduct(prod), HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable long id,@RequestBody Product prod) {
        return productService.replaceProduct(id, prod);
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable long id,@RequestBody Product prod) {
        return productService.updateProduct(id, prod);
    }

}
