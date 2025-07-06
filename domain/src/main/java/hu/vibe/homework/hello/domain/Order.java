package hu.vibe.homework.hello.domain;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Order(
    UUID id,
    UUID customerId,
    Instant createdAt,
    double totalPrice,
    List<OrderItem> items,
    OrderStatus status,
    Address shippingAddress,
    Address billingAddress
) {}


