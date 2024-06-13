package com.food.order.service.domain.service;

import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.entity.Restaurant;
import com.food.order.service.domain.event.OrderCancelledEvent;
import com.food.order.service.domain.event.OrderCreatedEvent;
import com.food.order.service.domain.event.OrderPaidEvent;

import java.time.ZonedDateTime;
import java.util.List;

public class OrderDomainServiceImpl implements OrderDomainService{
    @Override
    public OrderCreatedEvent validateAndInitiateOrder(Order order, Restaurant restaurant) {
        // validateRestaurant(restaurant)
        // setOrderProductInformation(order, restaurant)
        // order.validateOrder()
        // order.initializeOrder()

        return new OrderCreatedEvent(order, ZonedDateTime.now());
    }

    @Override
    public OrderPaidEvent payOrder(Order order) {
        // order.pay()
        return new OrderPaidEvent(order, ZonedDateTime.now());
    }

    @Override
    public OrderCancelledEvent cancelOrderPayment(Order order, List<String> failureMessages) {
        // order.cancel();
        return new OrderCancelledEvent(order, ZonedDateTime.now());
    }

    @Override
    public void approveOrder(Order order) {

    }

    @Override
    public void cancelOrder(Order order, List<String> failureMessages) {

    }
}
