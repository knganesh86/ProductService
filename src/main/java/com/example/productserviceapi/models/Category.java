package com.example.productserviceapi.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Entity
public class Category extends BaseModel {
    private String title;
    @JsonIgnore
    @OneToMany(mappedBy = "category")
    private List<Product> productList;
}
