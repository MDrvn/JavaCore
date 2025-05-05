package com.pizzeria.controller;

import com.pizzeria.dto.PizzaDTO;
import com.pizzeria.model.Pizza;
import com.pizzeria.service.PizzaService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PizzaController.class)
class PizzaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PizzaService pizzaService;

    @Test
    void createPizza_ShouldReturnCreated() throws Exception {
        Pizza pizza = new Pizza();
        pizza.setId(1L);
        when(pizzaService.createPizza(any(Pizza.class))).thenReturn(pizza);

        mockMvc.perform(post("/api/pizzas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Margherita\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void updatePizza_ShouldReturnUpdated() throws Exception {
        Pizza updated = new Pizza();
        updated.setId(1L);
        updated.setName("New Name");
        when(pizzaService.updatePizza(eq(1L), any(PizzaDTO.class))).thenReturn(updated);

        mockMvc.perform(put("/api/pizzas/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"New Name\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("New Name"));
    }

    @Test
    void deletePizza_ShouldCallService() throws Exception {
        mockMvc.perform(delete("/api/pizzas/1"))
                .andExpect(status().isOk());

        verify(pizzaService).softDeletePizza(1L);
    }
}