package com.pizzeria.service;

import com.pizzeria.model.PizzaOrder;
import com.pizzeria.repository.PizzaOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final PizzaOrderRepository orderRepository;

    @Transactional
    public PizzaOrder createOrder(PizzaOrder order) {
        return orderRepository.save(order);
    }

    public List<PizzaOrder> getAllOrders() {
        return orderRepository.findAll();
    }
}