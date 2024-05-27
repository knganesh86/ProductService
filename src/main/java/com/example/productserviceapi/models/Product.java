package com.example.productserviceapi.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
    private long id;
    private String name;
    private double price;
    private String description;
    private Category category;
}
