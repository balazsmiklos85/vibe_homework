package hu.vibe.homework.order.application;

import hu.vibe.homework.order.domain.Address;
import hu.vibe.homework.order.domain.Order;
import hu.vibe.homework.order.domain.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UpdateShippingAddressService implements hu.vibe.homework.order.domain.UpdateShippingAddressUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Order updateShippingAddress(UUID orderId, Address newAddress) {
        var orderOpt = orderRepository.findOrderById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }
        var order = orderOpt.get();
        order.status().verifyRedirectable();
        var updatedOrder = new Order(
                order.id(),
                order.customerId(),
                order.createdAt(),
                order.totalPrice(),
                order.items(),
                order.status(),
                newAddress,
                order.billingAddress());
        return orderRepository.save(updatedOrder);
    }
}
