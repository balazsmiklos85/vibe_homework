package hu.vibe.homework.hello.domain;

import java.util.Optional;

import java.util.List;

public interface OrderPort {
    Order createOrder(CreateOrderCommand command);
    Optional<Order> getOrder(Long id);

    record CreateOrderCommand(
        List<OrderItem> items,
        Long customerId,
        OrderStatus status,
        Address shippingAddress,
        Address billingAddress
    ) {}
}


