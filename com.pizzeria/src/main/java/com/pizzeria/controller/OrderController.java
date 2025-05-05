package com.pizzeria.controller;

import com.pizzeria.dto.OrderRequestDTO;
import com.pizzeria.model.PizzaOrder;
import com.pizzeria.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<PizzaOrder> createOrder(@RequestBody OrderRequestDTO orderRequest) {
        PizzaOrder order = modelMapper.map(orderRequest, PizzaOrder.class);
        return ResponseEntity.ok(orderService.createOrder(order));
    }

    @GetMapping
    public List<PizzaOrder> getAllOrders() {
        return orderService.getAllOrders();
    }
}