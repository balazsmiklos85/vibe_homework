package hu.vibe.homework.order.domain;

import java.math.BigDecimal;

public record OrderItem(String productCode, int quantity, BigDecimal unitPrice) {}
