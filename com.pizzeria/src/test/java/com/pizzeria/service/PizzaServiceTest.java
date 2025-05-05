package com.pizzeria.service;

import com.pizzeria.model.Pizza;
import com.pizzeria.repository.PizzaRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@Transactional
@AutoConfigureMockMvc
class PizzaServiceTest {
    @Autowired
    private PizzaRepository pizzaRepository;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testCreatePizza() {
        Pizza pizza = new Pizza();
        pizza.setName("Pepperoni");
        pizza.setPrice(new BigDecimal("12.99"));
        pizza.setQuantity(10);

        Pizza saved = pizzaRepository.save(pizza);
        assertNotNull(saved.getId());
    }

    @Test
    public void createOrder() throws Exception {
        String orderRequest = """
                {
                    "customerId": 0,
                    "items": []
                }""";

        mockMvc.perform(post("/api/orders")
                        .content(orderRequest)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllPizzas() throws Exception {
        mockMvc.perform(get("/api/pizzas"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void getAllOrders() throws Exception {
        mockMvc.perform(get("/api/orders"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void createPizza() throws Exception {
        String pizzaDTO = """
                {
                    "name": "",
                    "description": "",
                    "price": {},
                    "quantity": 0
                }""";

        mockMvc.perform(post("/api/pizzas")
                        .content(pizzaDTO)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    public void deletePizza() throws Exception {
        mockMvc.perform(delete("/api/pizzas/{0}", "0"))
                .andExpect(status().isOk())
                .andDo(print());
    }
}