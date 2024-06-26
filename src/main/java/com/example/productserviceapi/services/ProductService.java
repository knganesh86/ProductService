package com.example.productserviceapi.services;

import com.example.productserviceapi.models.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(Long id);
    List<Product> getAllProducts();
    Product createProduct(Product product);

    Product updateProduct(Long id, Product product);

    Product deleteProduct(Long id);
    Product replaceProduct(Long id, Product product);

}
