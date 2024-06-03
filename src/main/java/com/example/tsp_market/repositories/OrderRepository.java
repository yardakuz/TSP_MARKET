package com.example.tsp_market.repositories;

import com.example.tsp_market.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
     List<Order> findByUserId(Long userId);
//     List<Order> findByTechniqueId(Long techniqueId);
}
