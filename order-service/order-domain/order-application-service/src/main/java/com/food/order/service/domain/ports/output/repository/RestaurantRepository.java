package com.food.order.service.domain.ports.output.repository;

import com.food.order.service.domain.entity.Restaurant;

import javax.swing.text.html.Option;
import java.util.Optional;

public interface RestaurantRepository {
    Optional<Restaurant> find(Restaurant restaurant);
}
