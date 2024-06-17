package com.food.order.service.domain.entity;

import com.food.domain.entity.BaseEntity;
import com.food.domain.valueobject.OrderId;
import com.food.order.service.domain.valueobject.OrderItemId;

public class OrderItem extends BaseEntity<OrderItemId> {
    private OrderId orderId;
    private final Product product;

    private final int qty;

    private OrderItem(Builder builder) {
        super.setId(builder.orderItemId);
        orderId = builder.orderId;
        product = builder.product;
        qty = builder.qty;
    }

    public OrderId getOrderId() {
        return orderId;
    }

    public Product getProduct() {
        return product;
    }

    public int getQty() {
        return qty;
    }


    public static final class Builder {
        private OrderItemId orderItemId;
        private OrderId orderId;
        private Product product;
        private int qty;

        private Builder() {
        }

        public static Builder builder() {
            return new Builder();
        }

        public Builder orderItemId(OrderItemId val) {
            orderItemId = val;
            return this;
        }

        public Builder orderId(OrderId val) {
            orderId = val;
            return this;
        }

        public Builder product(Product val) {
            product = val;
            return this;
        }

        public Builder qty(int val) {
            qty = val;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}
