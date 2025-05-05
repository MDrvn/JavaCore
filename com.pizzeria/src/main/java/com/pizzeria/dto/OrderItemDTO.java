package com.pizzeria.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

@Data
public class OrderItemDTO {
    @NotNull(message = "Pizza ID is required")
    @Positive(message = "Invalid pizza ID")
    private Long pizzaId;

    @NotNull(message = "Quantity is required")
    @Min(value = 1, message = "Minimum quantity is 1")
    private Integer quantity;
}