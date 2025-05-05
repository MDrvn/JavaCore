package com.pizzeria.repository;

import com.pizzeria.model.PizzaOrder;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PizzaOrderRepository extends JpaRepository<PizzaOrder, Long> {}
