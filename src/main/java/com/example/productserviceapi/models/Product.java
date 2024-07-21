package com.example.productserviceapi.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Product extends BaseModel {
    private String name;
    private double price;
    private String description;
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Category category;
    private boolean isDeleted;
}
