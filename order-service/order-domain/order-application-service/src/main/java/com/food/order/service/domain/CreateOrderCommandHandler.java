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
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Component
public class CreateOrderCommandHandler {
    private final CreateOrderHelper createOrderHelper;
    private final OrderDataMapper orderDataMapper;
    private final OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher;
    private final ApplicationDomainEventPublisher applicationDomainEventPublisher;

    public CreateOrderCommandHandler(CreateOrderHelper createOrderHelper, OrderDataMapper orderDataMapper, OrderCreatedPaymentRequestMessagePublisher orderCreatedPaymentRequestMessagePublisher, ApplicationDomainEventPublisher applicationDomainEventPublisher) {
        this.createOrderHelper = createOrderHelper;
        this.orderDataMapper = orderDataMapper;
        this.orderCreatedPaymentRequestMessagePublisher = orderCreatedPaymentRequestMessagePublisher;
        this.applicationDomainEventPublisher = applicationDomainEventPublisher;
    }


    @Transactional
    CreateOrderResponse createOrder(CreateOrderCommand createOrderCommand){
        /* Since I used the default Spring proxy AOP, all AOP functionality provided by Spring,
        including transactional support, will only be effective if the call goes through the proxy.
        This means that annotated methods must be invoked from another bean.
        To address this, I created a new component, `OrderCreateHelper`.
        In the `OrderCreateCommandHandler`, if I create new methods with a transactional annotation
        and call them from within the `createOrder` methods, the transactional annotation will not be applied.
        Additionally, methods with a transactional annotation must be public;
        otherwise, the transactional annotation will not be effective.
        Note that if you use AspectJ instead of Spring Proxy AOP, you won't face this limitation.*/

        // Persist to DB
        OrderCreatedEvent orderCreatedEvent = createOrderHelper.persistOrder(createOrderCommand);

        // Direct Publish Message
        orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);

        // Another way to publish
        // Our Application Listener (OrderCreatedEventApplicationListener.java) calls
        // - orderCreatedPaymentRequestMessagePublisher.publish(orderCreatedEvent);
        //applicationDomainEventPublisher.publish(orderCreatedEvent);


        return orderDataMapper.orderToCreateOrderResponse(orderCreatedEvent.getOrder());


    }


}
