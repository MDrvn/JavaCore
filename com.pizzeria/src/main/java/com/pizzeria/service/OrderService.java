package com.pizzeria.service;

import com.pizzeria.dto.*;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.*;
import com.pizzeria.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final PizzaOrderRepository orderRepository;
    private final CustomerRepository customerRepository;
    private final PizzaRepository pizzaRepository;

    @Transactional
    public PizzaOrder createOrder(OrderRequestDTO request) {
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

        PizzaOrder order = new PizzaOrder();
        order.setCustomer(customer);
        order.setOrderDate(LocalDate.now());
        order.setStatus("NEW");

        request.getItems().forEach(item ->
                order.getItems().add(createOrderItem(item, order))
        );

        return orderRepository.save(order);
    }

    private OrderItem createOrderItem(OrderItemDTO itemDTO, PizzaOrder order) {
        Pizza pizza = pizzaRepository.findById(itemDTO.getPizzaId())
                .orElseThrow(() -> new ResourceNotFoundException("Pizza not found"));

        if(pizza.getQuantity() < itemDTO.getQuantity()) {
            throw new IllegalArgumentException("Not enough pizza quantity");
        }

        pizza.setQuantity(pizza.getQuantity() - itemDTO.getQuantity());
        pizzaRepository.save(pizza);

        OrderItem item = new OrderItem();
        item.setPizza(pizza);
        item.setQuantity(itemDTO.getQuantity());
        item.setOrder(order);
        return item;
    }
}