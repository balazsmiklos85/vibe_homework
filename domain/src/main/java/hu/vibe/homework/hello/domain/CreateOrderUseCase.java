package hu.vibe.homework.hello.domain;

import java.util.List;
import java.util.UUID;

public interface CreateOrderUseCase {
    Order createOrder(CreateOrderCommand command);

    record CreateOrderCommand(
            List<OrderItem> items,
            UUID customerId,
            OrderStatus status,
            Address shippingAddress,
            Address billingAddress) {}
}
