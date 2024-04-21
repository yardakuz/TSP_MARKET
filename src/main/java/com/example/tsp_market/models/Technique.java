package com.example.tsp_market.models;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Technique {
    private int id;
    private String title;
    private String description;
    private int price;
    private String image;
}
