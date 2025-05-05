package com.pizzeria.service;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.exception.ResourceNotFoundException;
import com.pizzeria.model.Pizza;
import com.pizzeria.repository.PizzaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PizzaService {
    private final PizzaRepository pizzaRepository;

    @Transactional
    public Pizza createPizza(Pizza pizza) {
        return pizzaRepository.save(pizza);
    }

    public List<Pizza> getAllPizzas() {
        return pizzaRepository.findAll();
    }

    @Transactional
    public Pizza updatePizza(Long id, PizzaDTO pizzaDTO) {
        Pizza pizza = pizzaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Pizza not found"));

        if (pizzaDTO.getName() != null) pizza.setName(pizzaDTO.getName());
        if (pizzaDTO.getDescription() != null) pizza.setDescription(pizzaDTO.getDescription());
        if (pizzaDTO.getPrice() != null) pizza.setPrice(pizzaDTO.getPrice());
        if (pizzaDTO.getQuantity() != null) pizza.setQuantity(pizzaDTO.getQuantity());

        return pizzaRepository.save(pizza);
    }

    @Transactional
    public void softDeletePizza(Long id) {
        pizzaRepository.findById(id).ifPresent(pizza -> {
            pizza.setDeleted(true);
            pizzaRepository.save(pizza);
        });
    }
}