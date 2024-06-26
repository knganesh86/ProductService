package com.example.productserviceapi.repository;

import com.example.productserviceapi.models.Category;
import com.example.productserviceapi.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CategoryRepo extends JpaRepository<Category, Long> {

    List<Category> findByTitle(String title);
}
