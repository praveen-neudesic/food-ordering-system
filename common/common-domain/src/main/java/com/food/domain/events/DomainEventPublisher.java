package com.food.domain.events;

public interface DomainEventPublisher<T extends DomainEvent> {
    void publish(T domainEvent);
}
