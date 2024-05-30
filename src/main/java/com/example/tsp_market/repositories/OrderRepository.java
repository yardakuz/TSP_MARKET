package com.example.tsp_market.repositories;

import com.example.tsp_market.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

}
