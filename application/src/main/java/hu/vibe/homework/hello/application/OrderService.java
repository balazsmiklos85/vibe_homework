package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderPort;
import hu.vibe.homework.hello.domain.OrderRepository;
import hu.vibe.homework.hello.domain.OrderItem;
import hu.vibe.homework.hello.domain.OrderStatus;
import hu.vibe.homework.hello.domain.Address;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.List;
import java.time.Instant;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderService implements OrderPort {

    private final OrderRepository orderRepository;


    @Override
    public Order createOrder(CreateOrderCommand command) {
        // Calculate total price
        double totalPrice = command.items().stream().mapToDouble(item -> item.unitPrice() * item.quantity()).sum();
        // Set creation timestamp
        Instant createdAt = Instant.now();
        // Map items
        List<OrderItem> items = command.items();
        // Map status
        OrderStatus status = command.status();
        // Map addresses
        Address shipping = new Address(
            command.shippingAddress().name(),
            command.shippingAddress().city(),
            command.shippingAddress().streetAddress(),
            command.shippingAddress().additionalStreetAddress(),
            command.shippingAddress().country(),
            command.shippingAddress().state(),
            command.shippingAddress().zipCode()
        );
        Address billing = new Address(
            command.billingAddress().name(),
            command.billingAddress().city(),
            command.billingAddress().streetAddress(),
            command.billingAddress().additionalStreetAddress(),
            command.billingAddress().country(),
            command.billingAddress().state(),
            command.billingAddress().zipCode()
        );
        Order order = new Order(
            null,
            createdAt,
            totalPrice,
            items,
            status,
            shipping,
            billing
        );
        return orderRepository.save(order);
    }


    @Override
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findOrderById(id);
    }
}
