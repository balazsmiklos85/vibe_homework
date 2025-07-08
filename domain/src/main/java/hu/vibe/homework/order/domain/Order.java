package hu.vibe.homework.order.domain;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public record Order(
        UUID id,
        UUID customerId,
        Instant createdAt,
        BigDecimal totalPrice,
        List<OrderItem> items,
        OrderStatus status,
        Address shippingAddress,
        Address billingAddress) {}
