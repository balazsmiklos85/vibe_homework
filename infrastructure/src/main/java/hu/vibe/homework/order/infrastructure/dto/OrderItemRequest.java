package hu.vibe.homework.order.infrastructure.dto;

import java.math.BigDecimal;

public record OrderItemRequest(String productCode, int quantity, BigDecimal unitPrice) {}
