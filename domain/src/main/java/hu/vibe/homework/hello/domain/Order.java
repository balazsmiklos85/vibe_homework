package hu.vibe.homework.hello.domain;

import java.time.Instant;
import java.util.List;

public record Order(
    Long id,
    Instant createdAt,
    double totalPrice,
    List<OrderItem> items,
    OrderStatus status,
    Address shippingAddress,
    Address billingAddress
) {}


