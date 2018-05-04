package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Game;
import org.springframework.data.repository.CrudRepository;

public interface GameRepo extends CrudRepository<Game,Long> {
}
