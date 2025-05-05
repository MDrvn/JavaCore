package com.pizzeria.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@SQLDelete(sql = "UPDATE order_item SET is_deleted = true WHERE id = ?")
@Where(clause = "is_deleted = false")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private PizzaOrder order;

    @ManyToOne
    @JoinColumn(name = "pizza_id")
    private Pizza pizza;

    private Integer quantity;
    private boolean isDeleted;
}