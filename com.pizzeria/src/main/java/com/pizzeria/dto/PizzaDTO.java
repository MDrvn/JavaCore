package com.pizzeria.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class PizzaDTO {
    private String name;
    private String description;
    private BigDecimal price;
    private Integer quantity;
}