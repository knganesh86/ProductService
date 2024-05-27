package com.example.productserviceapi.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FakeStoreProduct {
    private long id;
    private String title;
    private String description;
    private double price;
    private String image;
    private String category;
}
