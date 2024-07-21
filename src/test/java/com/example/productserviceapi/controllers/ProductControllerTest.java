package com.example.productserviceapi.controllers;

import com.example.productserviceapi.exceptions.ProductLimitExceedException;
import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.services.ProductService;
import lombok.Setter;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProductControllerTest {
    @MockBean
    ProductService productService;
    //FakeProductService productService;


    @Autowired
    ProductController productController;

    @BeforeEach
    void setup() {
        Product product = new Product();
        product.setName("Mackbook");
        product.setId(2L);
        //Rule
        when(productService.getProductById(any(Long.class))).thenReturn(product);
    }

    @Test
    void Test_WhenGetProductByIdIsCalled_ThenReturnProduct()
            throws ProductLimitExceedException {
        //Arrange

        //Act
        ResponseEntity<Product> responseEntity
                = productController.getProductById(2l);

        //Assert
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertNotNull(responseEntity.getBody());
        assertEquals(2L, responseEntity.getBody().getId());
        assertEquals("Mackbook", responseEntity.getBody().getName());
    }

    @Test
    void Test_WhenGetProductByIdIsCalled_ReturnException(){
        //Rule
        when(productService.getProductById(any(Long.class)))
                .thenThrow(new RuntimeException("Something went wrong"));

        assertThrows(RuntimeException.class, () -> productController.getProductById(2L));
    }

//    @Test
//    void Test_WhenGetProductByIdIsCalled_ThenCallRealProductService()
//            throws ProductLimitReachedExpection {
//        when(productService.getProductById(any(Long.class))).thenCallRealMethod();
//        //Act
//        ResponseEntity<Product> responseEntity
//                = productController.getProductbyId(2l);
//
//        //Assert
//        assertNotNull(responseEntity);
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertNotNull(responseEntity.getBody());
//        assertEquals(2L, responseEntity.getBody().getId());
//        assertEquals("Mackbook", responseEntity.getBody().getTitle());
//    }
}