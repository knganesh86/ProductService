package com.example.productserviceapi.exceptions;

public class ProductLimitExceedException extends RuntimeException{
    public ProductLimitExceedException(String message){
        super(message);
    }
}
