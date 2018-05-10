package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Product;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ProductRepo extends CrudRepository<Product,Long> {

    @Query("Select p from Product p where name=:name")
    Product findByName(@Param("name")String name);

    @Query("Select case when count(*) > 0 then true else false end from Product where name = :name")
    boolean doesExist(@Param("name") String name);
}
