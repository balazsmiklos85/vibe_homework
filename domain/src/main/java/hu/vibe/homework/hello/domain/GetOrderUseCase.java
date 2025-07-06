package hu.vibe.homework.hello.domain;

import java.util.Optional;
import java.util.UUID;

public interface GetOrderUseCase {
    Optional<Order> getOrder(UUID id);
}
