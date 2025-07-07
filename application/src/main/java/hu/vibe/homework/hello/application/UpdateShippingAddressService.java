package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.Address;
import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderRepository;
import hu.vibe.homework.hello.domain.OrderStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class UpdateShippingAddressService implements hu.vibe.homework.hello.domain.UpdateShippingAddressUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Order updateShippingAddress(UUID orderId, Address newAddress) {
        var orderOpt = orderRepository.findOrderById(orderId);
        if (orderOpt.isEmpty()) {
            throw new IllegalArgumentException("Order not found");
        }
        var order = orderOpt.get();
        var status = order.status();
        if (status == OrderStatus.SHIPPED || status == OrderStatus.DELIVERED || status == OrderStatus.CANCELED) {
            throw new IllegalStateException(
                    "Cannot update shipping address when order is SHIPPED, DELIVERED, or CANCELED.");
        }
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
