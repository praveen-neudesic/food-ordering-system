package com.food.order.service.domain.mapper;

import com.food.domain.valueobject.CustomerId;
import com.food.domain.valueobject.OrderId;
import com.food.domain.valueobject.RestaurantId;
import com.food.order.service.domain.dto.create.CreateOrderCommand;
import com.food.order.service.domain.dto.create.CreateOrderResponse;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.entity.Restaurant;
import org.springframework.stereotype.Component;

@Component
public class OrderDataMapper {

    // To Domain Objects
    public Restaurant createOrderCommandToRestaurant(CreateOrderCommand createOrderCommand) {
        return Restaurant.Builder.builder().restaurantId(new RestaurantId(createOrderCommand.getRestaurantId())).build();
    }

    public Order createOrderCommandToOrder(CreateOrderCommand createOrderCommand) {
        return Order.Builder.builder().customerId(new CustomerId(createOrderCommand.getCustomerId()))
                .restaurantId(new RestaurantId(createOrderCommand.getRestaurantId()))
                .build();
    }


    // From Domain Objects
    public CreateOrderResponse orderToCreateOrderResponse(Order order) {
        return CreateOrderResponse.builder()
                .orderStatus(order.getOrderStatus())
                .build();
    }

}
