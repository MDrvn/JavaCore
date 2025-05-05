package com.pizzeria.controller;

import com.pizzeria.dto.OrderRequestDTO;
import com.pizzeria.model.PizzaOrder;
import com.pizzeria.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<PizzaOrder> createOrder(
            @Valid @RequestBody OrderRequestDTO request
    ) {
        return ResponseEntity.ok(orderService.createOrder(request));
    }
}