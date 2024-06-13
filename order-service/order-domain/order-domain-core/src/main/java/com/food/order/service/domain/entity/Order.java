package com.food.order.service.domain.entity;

import com.food.domain.entity.AggregateRoot;
import com.food.domain.exception.DomainException;
import com.food.domain.valueobject.*;

import java.util.List;
import java.util.UUID;

public class Order extends AggregateRoot<OrderId> {
    private final CustomerId customerId;
    private final RestaurantId restaurantId;
    private final Money price;
    private final List<OrderItem> items;

    private OrderStatus orderStatus;

    private Order(Builder builder) {
        super.setId(builder.orderId);
        customerId = builder.customerId;
        restaurantId = builder.restaurantId;
        price = builder.price;
        items = builder.items;
        orderStatus = builder.orderStatus;
    }


    // Business Operations / Methods
    // initializeOrder()
    public void initializeOrder(){
        setId(new OrderId(UUID.randomUUID()));
        orderStatus = OrderStatus.PENDING;

        // Initialize OrderItems as well
        // Note that the Aggregate root is responsible for all entities
    }

    // validateOrder()


    // These are state changing methods
    // pay()
    public void pay(){
        if(orderStatus != OrderStatus.PENDING){
            throw new DomainException("Order Not in correct state");
        }
        orderStatus = OrderStatus.PAID;
    }
    // approve()
    public void approved(){
        if(orderStatus != OrderStatus.PAID){
            throw new DomainException("Order Not in correct state");
        }
        orderStatus = OrderStatus.APPROVED;

    }
    // initCancel()
    public void initCancel(){
        // This is for compensating transactions. To roll back the payment
        if(orderStatus != OrderStatus.PAID){
            throw new DomainException("Order Not in correct state");
        }
        orderStatus = OrderStatus.CANCELLING;
    }

    // cancel()


    public CustomerId getCustomerId() {
        return customerId;
    }

    public RestaurantId getRestaurantId() {
        return restaurantId;
    }

    public Money getPrice() {
        return price;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }


    // Builder is generated using innerBuilder plugin of Intellij

    public static final class Builder {
        private OrderId orderId;
        private CustomerId customerId;
        private RestaurantId restaurantId;
        private Money price;
        private List<OrderItem> items;
        private OrderStatus orderStatus;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder customerId(CustomerId val) {
            customerId = val;
            return this;
        }

        public Builder restaurantId(RestaurantId val) {
            restaurantId = val;
            return this;
        }

        public Builder price(Money val) {
            price = val;
            return this;
        }

        public Builder items(List<OrderItem> val) {
            items = val;
            return this;
        }

        public Builder orderStatus(OrderStatus val) {
            orderStatus = val;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}
