package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.Address;
import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderItem;
import hu.vibe.homework.hello.domain.OrderRepository;
import hu.vibe.homework.hello.domain.OrderStatus;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.time.Instant;
import java.util.List;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class CreateOrderService implements hu.vibe.homework.hello.domain.CreateOrderUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(hu.vibe.homework.hello.domain.CreateOrderUseCase.CreateOrderCommand command) {
        double totalPrice = command.items().stream()
                .mapToDouble(item -> item.unitPrice() * item.quantity())
                .sum();
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
