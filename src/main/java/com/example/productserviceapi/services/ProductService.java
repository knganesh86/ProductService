package com.example.productserviceapi.services;

import com.example.productserviceapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getPRoductById(long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);
    Product updateProduct(Product product);
    Product deleteProduct(long id);
    Product replaceProduct(Product product);

}
