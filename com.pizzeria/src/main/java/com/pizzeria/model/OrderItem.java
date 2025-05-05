package com.pizzeria.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;

@Data
@Entity
@SQLRestriction("is_deleted = false")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Pizza pizza;

    private Integer quantity;

    @ManyToOne
    private PizzaOrder order;

    private boolean isDeleted;
}