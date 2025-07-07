package hu.vibe.homework.order.domain;

import java.util.List;

public enum OrderStatus {
    CART,
    ORDERED,
    PAID,
    SHIPPED,
    DELIVERED,
    CANCELED;

    private static final List<OrderStatus> CANNOT_BE_REDIRECTED = List.of(SHIPPED, DELIVERED, CANCELED);

    public void verifyRedirectable() {
        if (CANNOT_BE_REDIRECTED.contains(this)) {
            throw new IllegalStateException(
                    "Cannot update shipping address when order is SHIPPED, DELIVERED, or CANCELED.");
        }
    }
}
