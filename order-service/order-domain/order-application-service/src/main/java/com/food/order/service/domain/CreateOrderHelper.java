package com.food.order.service.domain;

import com.food.domain.exception.DomainException;
import com.food.order.service.domain.dto.create.CreateOrderCommand;
import com.food.order.service.domain.dto.create.CreateOrderResponse;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.entity.Restaurant;
import com.food.order.service.domain.event.OrderCreatedEvent;
import com.food.order.service.domain.mapper.OrderDataMapper;
import com.food.order.service.domain.ports.output.message.publisher.payment.OrderCancelledPaymentRequestMessagePublisher;
import com.food.order.service.domain.ports.output.message.publisher.payment.OrderCreatedPaymentRequestMessagePublisher;
import com.food.order.service.domain.ports.output.repository.OrderRepository;
import com.food.order.service.domain.ports.output.repository.RestaurantRepository;
import com.food.order.service.domain.service.OrderDomainService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class CreateOrderHelper {
    private final OrderDomainService orderDomainService;
    private final OrderRepository orderRepository;
    private final RestaurantRepository restaurantRepository;
    private final OrderDataMapper orderDataMapper;

    public CreateOrderHelper(OrderDomainService orderDomainService, OrderRepository orderRepository, RestaurantRepository restaurantRepository, OrderDataMapper orderDataMapper, OrderCancelledPaymentRequestMessagePublisher orderCancelledPaymentRequestMessagePublisher, OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher) {
        this.orderDomainService = orderDomainService;
        this.orderRepository = orderRepository;
        this.restaurantRepository = restaurantRepository;
        this.orderDataMapper = orderDataMapper;
    }

    @Transactional
    OrderCreatedEvent persistOrder(CreateOrderCommand createOrderCommand){
        // Persist to DB
        // Talk to repository
        Restaurant restaurant = orderDataMapper.createOrderCommandToRestaurant(createOrderCommand);
        Optional<Restaurant> optionalRestaurant = restaurantRepository.find(restaurant);

        if(optionalRestaurant.isEmpty()){
            log.warn("restaurant {} not exist", restaurant);
            throw new DomainException("restaurant not exist");
        }

        // Call Domain Service
        Order order = orderDataMapper.createOrderCommandToOrder(createOrderCommand);
        OrderCreatedEvent orderCreatedEvent = orderDomainService.validateAndInitiateOrder(order, optionalRestaurant.get());

        // Save Order to db
        orderRepository.save(order);

        return orderCreatedEvent;
    }
}
