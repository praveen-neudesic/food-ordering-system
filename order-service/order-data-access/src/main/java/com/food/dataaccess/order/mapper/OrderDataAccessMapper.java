package com.food.dataaccess.order.mapper;

import com.food.dataaccess.order.entity.OrderEntity;
import com.food.dataaccess.order.entity.OrderItemEntity;
import com.food.domain.valueobject.*;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.entity.OrderItem;
import com.food.order.service.domain.entity.Product;
import com.food.order.service.domain.valueobject.OrderItemId;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

// To create Entity objects from domain objects and vice-versa
@Component
public class OrderDataAccessMapper {

    public OrderEntity orderToOrderEntity(Order order) {
        OrderEntity orderEntity = OrderEntity.builder()
                .id(order.getId().getValue())
                .customerId(order.getCustomerId().getValue())
                .restaurantId(order.getRestaurantId().getValue())

                .price(order.getPrice().getAmount())
                .items(orderItemsToOrderItemEntities(order.getItems()))
                .orderStatus(order.getOrderStatus())

                .build();

        orderEntity.getItems().forEach(orderItemEntity -> orderItemEntity.setOrder(orderEntity));

        return orderEntity;
    }

    public Order orderEntityToOrder(OrderEntity orderEntity) {
        return Order.Builder.builder()
                .orderId(new OrderId(orderEntity.getId()))
                .customerId(new CustomerId(orderEntity.getCustomerId()))
                .restaurantId(new RestaurantId(orderEntity.getRestaurantId()))
                .price(new Money(orderEntity.getPrice()))
                .items(orderItemEntitiesToOrderItems(orderEntity.getItems()))
                .orderStatus(orderEntity.getOrderStatus())
                .build();
    }

    private List<OrderItem> orderItemEntitiesToOrderItems(List<OrderItemEntity> items) {
        return items.stream()
                .map(orderItemEntity -> OrderItem.Builder.builder()
                        .orderItemId(new OrderItemId(orderItemEntity.getId()))
                        .product(new Product(new ProductId(orderItemEntity.getProductId()),"", new Money(orderItemEntity.getPrice())))
                        .build())
                .collect(Collectors.toList());
    }



    private List<OrderItemEntity> orderItemsToOrderItemEntities(List<OrderItem> items) {
        return items.stream()
                .map(orderItem -> OrderItemEntity.builder()
                        .id(orderItem.getId().getValue())
                        .productId(orderItem.getProduct().getId().getValue())
                        .build())
                .collect(Collectors.toList());
    }
}