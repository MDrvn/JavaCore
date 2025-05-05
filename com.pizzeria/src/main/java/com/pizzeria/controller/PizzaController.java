package com.pizzeria.controller;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.model.Pizza;
import com.pizzeria.service.PizzaService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
public class PizzaController {
    private final PizzaService pizzaService;
    private final ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody PizzaDTO pizzaDTO) {
        Pizza pizza = modelMapper.map(pizzaDTO, Pizza.class);
        return ResponseEntity.ok(pizzaService.createPizza(pizza));
    }

    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable Long id) {
        pizzaService.softDeletePizza(id);
    }
}