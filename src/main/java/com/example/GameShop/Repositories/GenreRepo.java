package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Genre;
import org.springframework.data.repository.CrudRepository;

public interface GenreRepo extends CrudRepository<Genre,Long> {
}
