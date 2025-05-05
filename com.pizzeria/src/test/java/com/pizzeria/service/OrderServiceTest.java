package com.pizzeria.service;

import com.pizzeria.dto.OrderItemDTO;
import com.pizzeria.dto.OrderRequestDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.*;
import com.pizzeria.repository.CustomerRepository;
import com.pizzeria.repository.PizzaOrderRepository;
import com.pizzeria.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.List;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private PizzaOrderRepository orderRepository;
    @Mock
    private CustomerRepository customerRepository;
    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void createOrder_ShouldCreateValidOrder() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerId(1L);
        request.setItems(List.of(new OrderItemDTO(1L, 2)));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer()));
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(new Pizza()));
        when(orderRepository.save(any(PizzaOrder.class))).thenReturn(new PizzaOrder());

        PizzaOrder result = orderService.createOrder(request);

        assertNotNull(result);
        verify(orderRepository).save(any(PizzaOrder.class));
    }

    @Test
    void createOrder_ShouldThrowWhenCustomerNotFound() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerId(999L);

        when(customerRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                orderService.createOrder(request));
    }

    @Test
    void createOrder_ShouldThrowWhenPizzaNotFound() {
        OrderRequestDTO request = new OrderRequestDTO();
        request.setCustomerId(1L);
        request.setItems(List.of(new OrderItemDTO(999L, 1)));

        when(customerRepository.findById(1L)).thenReturn(Optional.of(new Customer()));
        when(pizzaRepository.findById(999L)).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                orderService.createOrder(request));
    }
}