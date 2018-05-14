package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

public interface GenreRepo extends CrudRepository<Genre,Long> {

    @Query("Select case when count(*) > 0 then true else false end from Genre where name = :name")
    public boolean existsWithName(@Param("name")String name);
}
