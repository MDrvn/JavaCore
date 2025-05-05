package com.pizzeria.model;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.SQLRestriction;
import java.math.BigDecimal;

@Data
@Entity
@SQLRestriction("is_deleted = false")
public class Pizza {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
    private boolean isDeleted;
}