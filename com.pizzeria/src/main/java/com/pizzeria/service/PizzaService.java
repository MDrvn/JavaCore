package com.pizzeria.service;

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
    public void softDeletePizza(Long id) {
        pizzaRepository.findById(id).ifPresent(pizza -> {
            pizza.setDeleted(true);
            pizzaRepository.save(pizza);
        });
    }
}