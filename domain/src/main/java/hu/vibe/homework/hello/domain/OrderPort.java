package hu.vibe.homework.hello.domain;

import java.util.Optional;

public interface OrderPort {
    Order createOrder(String description);
    Optional<Order> getOrder(Long id);
}
