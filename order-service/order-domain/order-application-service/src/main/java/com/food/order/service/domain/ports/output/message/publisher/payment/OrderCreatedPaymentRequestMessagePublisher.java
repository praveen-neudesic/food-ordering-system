package com.food.order.service.domain.ports.output.message.publisher.payment;

import com.food.domain.events.DomainEventPublisher;
import com.food.order.service.domain.event.OrderCreatedEvent;

public interface OrderCreatedPaymentRequestMessagePublisher extends DomainEventPublisher<OrderCreatedEvent> {
}
