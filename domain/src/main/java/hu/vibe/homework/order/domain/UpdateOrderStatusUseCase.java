package hu.vibe.homework.order.domain;

import java.util.UUID;

public interface UpdateOrderStatusUseCase {

    void updateOrderStatus(UUID orderId, OrderStatus newStatus);
}
