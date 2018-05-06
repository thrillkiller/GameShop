package com.example.GameShop.Repositories;

import com.example.GameShop.Models.EOrder;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<EOrder,Long> {
}
