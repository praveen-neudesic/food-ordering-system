package com.food.order.service.domain;

import com.food.domain.events.DomainEventPublisher;
import com.food.order.service.domain.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationDomainEventPublisher implements ApplicationEventPublisherAware, DomainEventPublisher<OrderCreatedEvent> {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void publish(OrderCreatedEvent domainEvent) {
        this.applicationEventPublisher.publishEvent(domainEvent);
        log.info("Event published: {}", domainEvent);
    }
}
