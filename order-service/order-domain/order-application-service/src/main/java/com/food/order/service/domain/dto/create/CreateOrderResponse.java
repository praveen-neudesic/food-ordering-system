package com.food.order.service.domain.dto.create;

import com.food.domain.valueobject.OrderStatus;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@AllArgsConstructor
public class CreateOrderResponse {
    @NotNull
    private final OrderStatus orderStatus;

    @NotNull
    private final String message;
}
