package com.example.productserviceapi.controllers;

import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.services.ProductService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/products")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable long id) {
        return productService.getPRoductById(id);
    }

    @GetMapping
    public ArrayList<Product> getAllProducts(@PathVariable long id) {
        return new ArrayList<Product>();
    }

    @PostMapping
    public Product createProduct(@RequestBody Product prod) {
        return prod;
    }
    @PutMapping("/{id}")
    public Product replaceProduct(@PathVariable long id,@RequestBody Product prod) {
        return prod;
    }
    @PatchMapping("/{id}")
    public Product updateProduct(@PathVariable long id,@RequestBody Product prod) {
        return prod;
    }

}
