package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Eorder;
import com.example.GameShop.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepo extends CrudRepository<Eorder,Long> {

    @Query("Select o from Eorder o where user =:user")
    Iterable<Eorder> findByUser(@Param("user") User user);
}
