package com.food.order.service.domain.ports.input.message.listener.restaurantapproval;

import com.food.order.service.domain.dto.messsage.RestaurantApprovalResponse;

public interface RestaurantApprovalResponseMessageListener {
    void OrderApproved(RestaurantApprovalResponse restaurantApprovalResponse);
    void OrderRejected(RestaurantApprovalResponse restaurantApprovalResponse);
}
