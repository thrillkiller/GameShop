package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Game;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GameRepo extends CrudRepository<Game,Long> {

    @Query("Select g from Game g where name=:name")
    Game findByName(@Param("name")String name);

    @Query("Select case when count(*) > 0 then true else false end from Game where name = :name")
    boolean doesExist(@Param("name") String name);
}
