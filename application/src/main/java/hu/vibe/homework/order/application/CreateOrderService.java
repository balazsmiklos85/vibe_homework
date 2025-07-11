package hu.vibe.homework.order.application;

import hu.vibe.homework.order.domain.Address;
import hu.vibe.homework.order.domain.Order;
import hu.vibe.homework.order.domain.OrderItem;
import hu.vibe.homework.order.domain.OrderRepository;
import hu.vibe.homework.order.domain.OrderStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CreateOrderService implements hu.vibe.homework.order.domain.CreateOrderUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(hu.vibe.homework.order.domain.CreateOrderUseCase.CreateOrderCommand command) {
        BigDecimal totalPrice = command.items().stream()
                .map(item -> item.unitPrice().multiply(BigDecimal.valueOf(item.quantity())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        Instant createdAt = Instant.now();
        List<OrderItem> items = command.items();
        OrderStatus status = command.status();
        Address shipping = new Address(
                command.shippingAddress().name(),
                command.shippingAddress().city(),
                command.shippingAddress().streetAddress(),
                command.shippingAddress().additionalStreetAddress(),
                command.shippingAddress().country(),
                command.shippingAddress().state(),
                command.shippingAddress().zipCode());
        Address billing = new Address(
                command.billingAddress().name(),
                command.billingAddress().city(),
                command.billingAddress().streetAddress(),
                command.billingAddress().additionalStreetAddress(),
                command.billingAddress().country(),
                command.billingAddress().state(),
                command.billingAddress().zipCode());
        Order order = new Order(null, command.customerId(), createdAt, totalPrice, items, status, shipping, billing);
        return orderRepository.save(order);
    }
}
