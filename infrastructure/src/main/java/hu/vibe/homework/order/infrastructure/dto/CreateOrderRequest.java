package hu.vibe.homework.order.infrastructure.dto;

import java.util.List;
import java.util.UUID;

public record CreateOrderRequest(
        List<OrderItemRequest> items,
        UUID customerId,
        String status,
        AddressRequest shippingAddress,
        AddressRequest billingAddress) {}
