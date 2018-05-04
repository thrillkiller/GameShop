package com.example.GameShop.Repositories;

import com.example.GameShop.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User,Long> {

    @Query("Select u From User u where login =:login")
    User getUserByLogin(@Param("login")String login);
}
