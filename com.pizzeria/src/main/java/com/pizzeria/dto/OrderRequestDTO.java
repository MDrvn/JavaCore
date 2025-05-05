package com.pizzeria.dto;

import lombok.Data;
import java.util.List;

@Data
public class OrderRequestDTO {
    private Long customerId;
    private List<OrderItemDTO> items;
}