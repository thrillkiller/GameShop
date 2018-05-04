package com.example.GameShop.Repositories;

import com.example.GameShop.Models.Order;
import org.springframework.data.repository.CrudRepository;

public interface OrderRepo extends CrudRepository<Order,Long> {
}
