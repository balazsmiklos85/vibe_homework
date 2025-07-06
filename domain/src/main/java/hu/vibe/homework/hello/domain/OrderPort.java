package hu.vibe.homework.hello.domain;

import java.util.Optional;
import java.util.List;
import java.util.UUID;

public interface OrderPort {
    Order createOrder(CreateOrderCommand command);
    Optional<Order> getOrder(UUID id);
    /**
     * Updates the shipping address of an existing order, unless the order is SHIPPED, DELIVERED, or CANCELED.
     * Throws an exception or returns error if not allowed.
     */
    Order updateShippingAddress(UUID orderId, Address newAddress);

    record CreateOrderCommand(
        List<OrderItem> items,
        UUID customerId,
        OrderStatus status,
        Address shippingAddress,
        Address billingAddress
    ) {}
}


