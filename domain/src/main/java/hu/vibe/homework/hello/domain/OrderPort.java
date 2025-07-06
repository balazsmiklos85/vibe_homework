package hu.vibe.homework.hello.domain;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface OrderPort {
    Order createOrder(CreateOrderCommand command);
    Optional<Order> getOrder(UUID id);

    record CreateOrderCommand(
        List<OrderItem> items,
        UUID customerId,
        OrderStatus status,
        Address shippingAddress,
        Address billingAddress
    ) {}
}


