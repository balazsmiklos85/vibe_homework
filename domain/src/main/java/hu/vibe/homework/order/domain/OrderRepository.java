package hu.vibe.homework.order.domain;

import java.util.Optional;

public interface OrderRepository {
    Optional<Order> findOrderById(java.util.UUID id);

    Order save(Order order);
}
