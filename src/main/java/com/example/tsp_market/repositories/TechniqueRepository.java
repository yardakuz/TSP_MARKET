package com.example.tsp_market.repositories;

import com.example.tsp_market.models.Technique;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TechniqueRepository extends JpaRepository<Technique, Long> {
    List<Technique> findByTitle(String title);
}
