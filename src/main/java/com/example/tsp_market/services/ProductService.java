package com.example.tsp_market.services;


import com.example.tsp_market.models.Technique;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {
    private List<Technique> products = new ArrayList<>();
    private long ID = 0;
    {
        products.add(new Technique(++ID,"PlayStation 5", "Simple description", 1000, "IMAGE"));
        products.add(new Technique(++ID ,"Nothingphone", "Simple description", 2000, "IMAGE"));
    }

    public List<Technique> listProducts() {return products;}

    public void saveProduct(Technique product) {
        product.setID(++ID);
        products.add(product);
    }

    public void removeProduct(Long id) {
        products.removeIf(product -> product.getID().equals(id));
    }

    public Technique getProductById(Long id) {
        for (Technique product : products) {
            if (product.getID().equals(id)) return product;
        }
        return null;
    }
}
