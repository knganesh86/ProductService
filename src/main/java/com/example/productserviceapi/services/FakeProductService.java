package com.example.productserviceapi.services;

import com.example.productserviceapi.dtos.FakeStoreProduct;
import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.models.Category;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
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
        FakeStoreProduct fakeStoreProducts[] = this.restTemplate.getForObject("https://fakestoreapi.com/products/", FakeStoreProduct[].class);
        List<Product> products = new ArrayList<>();
        for (FakeStoreProduct fakeStoreProduct: fakeStoreProducts) {
            products.add(convertFakeStoreProductToProduct(fakeStoreProduct));
        }
        return products;
    }

    @Override
    public Product createProduct(Product product) {
        FakeStoreProduct responseObject = this.restTemplate.postForObject("https://fakestoreapi.com/products", convertProductToFakeStoreProduct(product), FakeStoreProduct.class);
        return convertFakeStoreProductToProduct(responseObject);
    }

    @Override
    public Product updateProduct(Long id, Product product) {
        FakeStoreProduct fakeStoreProductDto = convertProductToFakeStoreProduct(product);
        fakeStoreProductDto = restTemplate.patchForObject("https://fakestoreapi.com/products/" + id,
                fakeStoreProductDto, FakeStoreProduct.class);
        return convertFakeStoreProductToProduct(fakeStoreProductDto);
    }

    @Override
    public Product deleteProduct(long id) {
        return null;
    }

    @Override
    public Product replaceProduct(long id, Product product) {
        FakeStoreProduct fakeStoreProductDto = convertProductToFakeStoreProduct(product);
        restTemplate.put("https://fakestoreapi.com/products/" + id,
                fakeStoreProductDto, FakeStoreProduct.class);
        return convertFakeStoreProductToProduct(fakeStoreProductDto);
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

    public FakeStoreProduct convertProductToFakeStoreProduct(Product product) {
        // Create a new FakeStoreProduct instance
        FakeStoreProduct fakeStoreProduct = new FakeStoreProduct();

        // Set the fields of FakeStoreProduct using the Product object
        fakeStoreProduct.setId(product.getId());
        fakeStoreProduct.setTitle(product.getName());
        fakeStoreProduct.setDescription(product.getDescription());
        fakeStoreProduct.setPrice(product.getPrice());

        // Assuming image is not available in Product, you can set it to a default value or null
        fakeStoreProduct.setImage(null);  // Or some default image URL

        // Set the category title from Product's Category
        if (product.getCategory() != null) {
            fakeStoreProduct.setCategory(product.getCategory().getTitle());
        } else {
            fakeStoreProduct.setCategory(null);  // Or some default category
        }

        return fakeStoreProduct;
    }
}
