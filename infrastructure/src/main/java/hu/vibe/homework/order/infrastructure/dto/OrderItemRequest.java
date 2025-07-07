package hu.vibe.homework.order.infrastructure.dto;

public record OrderItemRequest(String productCode, int quantity, double unitPrice) {}
