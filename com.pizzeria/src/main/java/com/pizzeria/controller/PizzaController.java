package com.pizzeria.controller;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.model.Pizza;
import com.pizzeria.service.PizzaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pizzas")
@RequiredArgsConstructor
@Tag(name = "Управление пиццами", description = "Операции с пиццами в меню")
public class PizzaController {
    private final PizzaService pizzaService;

    @Operation(summary = "Создать новую пиццу", description = "Добавляет новую пиццу в меню")
    @ApiResponse(responseCode = "200", description = "Пицца успешно создана")
    @PostMapping
    public ResponseEntity<Pizza> createPizza(@RequestBody Pizza pizza) {
        return ResponseEntity.ok(pizzaService.createPizza(pizza));
    }

    @Operation(summary = "Получить все пиццы", description = "Возвращает список всех доступных пицц")
    @ApiResponse(responseCode = "200", description = "Список успешно получен")
    @GetMapping
    public List<Pizza> getAllPizzas() {
        return pizzaService.getAllPizzas();
    }

    @Operation(summary = "Обновить пиццу", description = "Обновляет информацию о существующей пицце")
    @ApiResponse(responseCode = "200", description = "Пицца успешно обновлена")
    @PutMapping("/{id}")
    public ResponseEntity<Pizza> updatePizza(
            @PathVariable Long id,
            @RequestBody PizzaDTO pizzaDTO) {
        return ResponseEntity.ok(pizzaService.updatePizza(id, pizzaDTO));
    }

    @Operation(summary = "Удалить пиццу", description = "Помечает пиццу как удаленную (soft delete)")
    @ApiResponse(responseCode = "200", description = "Пицца успешно удалена")
    @DeleteMapping("/{id}")
    public void deletePizza(@PathVariable Long id) {
        pizzaService.softDeletePizza(id);
    }
}