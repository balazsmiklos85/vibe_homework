package hu.vibe.homework.hello.infrastructure.dto;

import java.util.List;

public record CreateOrderRequest(
    List<OrderItemRequest> items,
    String status,
    AddressRequest shippingAddress,
    AddressRequest billingAddress
) {}


