package hu.vibe.homework.hello.infrastructure.dto;

public record OrderItemRequest(String productCode, int quantity, double unitPrice) {}
