package com.food.order.service.domain.ports.input.service;

import com.food.order.service.domain.dto.create.CreateOrderCommand;
import com.food.order.service.domain.dto.create.CreateOrderResponse;
import jakarta.validation.Valid;

public interface OrderApplicationService {
    CreateOrderResponse createOrder(@Valid CreateOrderCommand createOrderCommand);
}
