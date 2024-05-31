package com.example.tsp_market.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "order-technique")
public class OrderTechnique {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE )
    @Column(name = "id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technique_id", nullable = false)
    private Technique technique;

    @Column(name = "amount", nullable = false)
    private Integer amount;

}