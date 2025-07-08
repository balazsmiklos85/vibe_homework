package hu.vibe.homework.order.application;

import hu.vibe.homework.order.domain.Order;
import hu.vibe.homework.order.domain.OrderRepository;
import hu.vibe.homework.order.domain.OrderStatus;
import hu.vibe.homework.order.domain.UpdateOrderStatusUseCase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UpdateOrderStatusService implements UpdateOrderStatusUseCase {

    private final OrderRepository orderRepository;

    @Override
    public void updateOrderStatus(UUID orderId, OrderStatus newStatus) {
        Order order = orderRepository.findOrderById(orderId)
                .orElseThrow(() -> new IllegalArgumentException("Order not found with id: " + orderId));

        Order updatedOrder = new Order(
                order.id(),
                order.customerId(),
                order.createdAt(),
                order.totalPrice(),
                order.items(),
                newStatus,
                order.shippingAddress(),
                order.billingAddress()
        );

        orderRepository.save(updatedOrder);
    }
}
