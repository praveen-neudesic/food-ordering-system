package com.food.order.service.domain.service;

import com.food.domain.valueobject.RestaurantId;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.entity.Restaurant;
import com.food.order.service.domain.event.OrderCancelledEvent;
import com.food.order.service.domain.event.OrderCreatedEvent;
import com.food.order.service.domain.event.OrderPaidEvent;

import java.util.List;

/*
* Although the domain events are created & returned from the domain core / service, event firing process will be on the caller service,
* i.e. application Service
* Before firing an event, underlying business operation should be persisted in DB first (for consistency)
* Delegate the repository calls to application service, as domain should only focus on business logic
*/
public interface OrderDomainService {

    // Domain Service is used if business logic cannot fit into a single aggregate root
    // In DDD, domain service is not mandatory
    OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant);

    OrderPaidEvent payOrder(Order order);

    OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages);

    void approveOrder(Order order);
    void cancelOrder(Order order, List<String> failureMessages);
}
