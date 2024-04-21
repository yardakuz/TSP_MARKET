package com.example.tsp_market.services;


import com.example.tsp_market.models.Technique;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Technique> products = new ArrayList<>();
    private int id = 0;
    {
        products.add(new Technique(++id,"PlayStation 5", "Simple description", 1000, "IMAGE"));
        products.add(new Technique(++id ,"Nothingphone", "Simple description", 2000, "IMAGE"));
    }

    public List<Technique> listProducts() {return products;}

    public void saveProduct(Technique product) {
        product.setId(++id);
        products.add(product);
    }

    public void removeProduct(int id) {
        products.removeIf(product -> product.getId() == id);
    }

    public Technique getProductById(int id) {
        for (Technique product : products) {
            if (product.getId() == id) return product;
        }
        return null;
    }
}
