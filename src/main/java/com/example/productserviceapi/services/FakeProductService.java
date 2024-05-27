package com.example.productserviceapi.services;

import com.example.productserviceapi.dtos.FakeStoreProduct;
import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class FakeProductService implements ProductService{

    private RestTemplate restTemplate ;

    public FakeProductService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Product getPRoductById(long id) {
        FakeStoreProduct fakeStoreProduct = this.restTemplate.getForObject("https://fakestoreapi.com/products/" + id, FakeStoreProduct.class);
        return convertFakeStoreProductToProduct(fakeStoreProduct);
    }

    @Override
    public List<Product> getAllProducts() {
        return List.of();
    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product updateProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(long id) {
        return null;
    }

    @Override
    public Product replaceProduct(Product product) {
        return null;
    }

    public Product convertFakeStoreProductToProduct(FakeStoreProduct fakeStoreProduct) {
        Product product = new Product();
        product.setId(fakeStoreProduct.getId());
        product.setName(fakeStoreProduct.getTitle());
        product.setPrice(fakeStoreProduct.getPrice());
        product.setDescription(fakeStoreProduct.getDescription());

        // Assuming you have a method to convert category as well
        product.setCategory(convertCategory(fakeStoreProduct.getCategory()));

        return product;
    }

    private Category convertCategory(String categoryTitle) {
        Category category = new Category();
        // You can fetch category details based on the category title from database or any other source
        category.setTitle(categoryTitle);
        return category;
    }
}
