package com.example.productserviceapi.repository;


import com.example.productserviceapi.models.Product;
import com.example.productserviceapi.projections.ProductwithTitleandDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepo extends JpaRepository<Product, Long> {

    //HQL
    @Query("select p.name as title, p.description as description from Product p where p.id=:id")
    ProductwithTitleandDesc someRandomQuery(@Param("id") Long id);

    //SQL
    @Query(value = "select name, description from product where id = :id", nativeQuery = true)
    ProductwithTitleandDesc someRandomQuery1(@Param("id") Long id);
}
