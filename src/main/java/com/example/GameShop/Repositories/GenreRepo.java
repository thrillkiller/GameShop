package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Genre;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface GenreRepo extends CrudRepository<Genre,Long> {

    @Query("Select case when count(*) > 0 then true else false end from Genre where name = :name")
   boolean existsWithName(@Param("name")String name);

    @Query("Select distinct g from Genre g where name =:name")
   Genre findByName(@Param("name")String name);

    @Query("Select distinct g.name from Genre g")
    Iterable<String> selectGenresNames();
}
