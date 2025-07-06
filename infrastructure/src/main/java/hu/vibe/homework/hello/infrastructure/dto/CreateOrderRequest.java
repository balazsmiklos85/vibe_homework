package hu.vibe.homework.hello.infrastructure.dto;

import java.util.List;

public record CreateOrderRequest(
    List<OrderItemRequest> items,
    Long customerId,
    String status,
    AddressRequest shippingAddress,
    AddressRequest billingAddress
) {}


