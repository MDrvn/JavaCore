package com.pizzeria.dto;

import lombok.Data;

@Data
public class OrderItemDTO {
    private Long pizzaId;
    private Integer quantity;
}