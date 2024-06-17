package com.food.dataaccess.order.adapter;

import com.food.dataaccess.order.entity.OrderEntity;
import com.food.dataaccess.order.mapper.OrderDataAccessMapper;
import com.food.dataaccess.order.repository.OrderJpaRepository;
import com.food.domain.valueobject.OrderId;
import com.food.order.service.domain.entity.Order;
import com.food.order.service.domain.ports.output.repository.OrderRepository;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class OrderRepositoryImpl implements OrderRepository {

    private final OrderJpaRepository orderJpaRepository;
    private final OrderDataAccessMapper orderDataAccessMapper;

    public OrderRepositoryImpl(OrderJpaRepository orderJpaRepository,
                               OrderDataAccessMapper orderDataAccessMapper) {
        this.orderJpaRepository = orderJpaRepository;
        this.orderDataAccessMapper = orderDataAccessMapper;
    }

    @Override
    public Order save(Order order) {

        // Convert domain object to entity object and save
        OrderEntity orderEntity = orderJpaRepository.save(orderDataAccessMapper.orderToOrderEntity(order));

        // Return domain object from entity object
        return orderDataAccessMapper.orderEntityToOrder(orderEntity);
    }

    public Optional<Order> findById(OrderId orderId) {
        return orderJpaRepository.findById(orderId.getValue()).map(orderDataAccessMapper::orderEntityToOrder);
    }
}