package com.example.tsp_market.services;


import com.example.tsp_market.models.Technique;
import com.example.tsp_market.repositories.TechniqueRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProductService {
    private final TechniqueRepository techniqueRepository;

    public List<Technique> listProducts(String title) {
        if(title != null) return techniqueRepository.findByTitle(title);
        return techniqueRepository.findAll();
    }

    public void saveProduct(Technique product) {
        log.info("Saving new {}", product);
        techniqueRepository.save(product);
    }

    public void removeProduct(Long id) {
        techniqueRepository.deleteById(id);
    }

    public Technique getProductById(Long id) {
       return techniqueRepository.findById(id).orElse(null);
    }
}
