package com.food.order.service.domain.ports.output.message.publisher.restaurantapproval;

import com.food.domain.events.DomainEventPublisher;
import com.food.order.service.domain.event.OrderPaidEvent;

public interface OrderPaidRestaurantMessagePublisher extends DomainEventPublisher<OrderPaidEvent> {
}
