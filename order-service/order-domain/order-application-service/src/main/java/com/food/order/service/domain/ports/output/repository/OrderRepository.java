package com.food.order.service.domain.ports.output.repository;

import com.food.order.service.domain.entity.Order;

public interface OrderRepository {
    // Order - Domain Entity (Aggregate Root)
    Order save(Order order);
}
