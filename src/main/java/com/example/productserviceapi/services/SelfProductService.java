package com.example.productserviceapi.services;

import com.example.productserviceapi.dtos.FakeStoreProduct;
import com.example.productserviceapi.models.Category;
import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.repository.CategoryRepo;
import com.example.productserviceapi.repository.ProductRepo;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Primary
public class SelfProductService implements ProductService{

    private ProductRepo productRepo;
    private CategoryRepo categoryRepo;

    public SelfProductService(ProductRepo productRepo, CategoryRepo categoryRepo) {
        this.productRepo = productRepo;
        this.categoryRepo = categoryRepo;
    }


    @Override
    public Product getProductById(Long id) {
        Optional<Product> product = productRepo.findById(id);
        if(product.isEmpty()) {
            throw new EntityNotFoundException("ProductNot Found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepo.findAll();
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if(existingProduct.isEmpty()) {
            throw new EntityNotFoundException("ProductNot Found");
        }
      Product  oldProd = existingProduct.get();
        oldProd.setPrice(product.getPrice());
        oldProd.setName(product.getName());
        oldProd.setDescription(product.getDescription());
        oldProd.setCategory(product.getCategory());
        return productRepo.save(oldProd);
    }

    @Override
    public Product deleteProduct(Long id) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if(existingProduct.isEmpty()) {
            throw new EntityNotFoundException("ProductNot Found");
        }
        Product  oldProd = existingProduct.get();
        productRepo.delete(oldProd);
        return oldProd;
    }

    @Override
    public Product replaceProduct(Long id, Product product) {
        Optional<Product> existingProduct = productRepo.findById(id);
        if(existingProduct.isEmpty()) {
            throw new EntityNotFoundException("ProductNot Found");
        }
        Product  oldProd = existingProduct.get();
        oldProd.setPrice(product.getPrice());
        oldProd.setName(product.getName());
        oldProd.setDescription(product.getDescription());
        oldProd.setCategory(product.getCategory());
        return productRepo.save(oldProd);
    }

    @Override
    public Product createProduct(Product product) {
        Category category= product.getCategory();
        category.setCreatedAt(LocalDateTime.now());
        if(category.getId() == null) {List<Category> categoryList = categoryRepo.findByTitle(product.getCategory().getTitle());
            if (!categoryList.isEmpty()) {
                product.setCategory(categoryList.get(0));
            }
            else {
                Category savedCategory = categoryRepo.save(category);
                product.setCategory(savedCategory);
            }

        }
        else {
            Category savedCategory = categoryRepo.save(category);
            product.setCategory(savedCategory);
        }
        product.setCreatedAt(LocalDateTime.now());
        return productRepo.save(product);
    }


}
