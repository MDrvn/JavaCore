package com.pizzeria.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    @NotNull(message = "Customer ID is required")
    @Positive(message = "Invalid customer ID")
    private Long customerId;

    @Valid
    @NotEmpty(message = "Order items cannot be empty")
    private List<OrderItemDTO> items;
}