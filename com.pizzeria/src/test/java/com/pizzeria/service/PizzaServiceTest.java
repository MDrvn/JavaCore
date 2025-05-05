package com.pizzeria.service;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Pizza;
import com.pizzeria.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.math.BigDecimal;
import java.util.Optional;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PizzaServiceTest {

    @Mock
    private PizzaRepository pizzaRepository;

    @InjectMocks
    private PizzaService pizzaService;

    @Test
    void createPizza_ShouldSaveAndReturnPizza() {
        Pizza pizza = new Pizza();
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(pizza);

        Pizza result = pizzaService.createPizza(pizza);

        assertNotNull(result);
        verify(pizzaRepository).save(pizza);
    }

    @Test
    void updatePizza_ShouldUpdateExistingPizza() {
        Pizza existing = new Pizza();
        existing.setId(1L);
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(pizzaRepository.save(any(Pizza.class))).thenReturn(existing);

        PizzaDTO dto = new PizzaDTO();
        dto.setName("New Name");
        dto.setPrice(BigDecimal.valueOf(15.99));

        Pizza result = pizzaService.updatePizza(1L, dto);

        assertEquals("New Name", result.getName());
        assertEquals(BigDecimal.valueOf(15.99), result.getPrice());
    }

    @Test
    void updatePizza_ShouldThrowWhenNotFound() {
        when(pizzaRepository.findById(anyLong())).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () ->
                pizzaService.updatePizza(999L, new PizzaDTO()));
    }

    @Test
    void softDelete_ShouldMarkAsDeleted() {
        Pizza pizza = new Pizza();
        when(pizzaRepository.findById(1L)).thenReturn(Optional.of(pizza));

        pizzaService.softDeletePizza(1L);

        assertTrue(pizza.isDeleted());
        verify(pizzaRepository).save(pizza);
    }
}