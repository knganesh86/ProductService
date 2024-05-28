package com.example.productserviceapi.services;

import com.example.productserviceapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getPRoductById(long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product deleteProduct(long id);
    Product replaceProduct(long id, Product product);

}
