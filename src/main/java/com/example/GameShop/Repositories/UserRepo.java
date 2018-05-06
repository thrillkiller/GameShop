package com.example.GameShop.Repositories;

import com.example.GameShop.Models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface UserRepo extends CrudRepository<User,Long> {

    @Query("Select u From User u where username =:login")
    User getUserByLogin(@Param("login")String login);

    @Query("select case when count(*) > 0 then true else false end from User where username =:login")
    boolean loginExists(@Param("login")String login);
}
