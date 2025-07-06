package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderPort;
import hu.vibe.homework.hello.domain.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderService implements OrderPort {

    private final OrderRepository orderRepository;

    @Override
    public Order createOrder(String description) {
        Order order = new Order(null, description);
        return orderRepository.save(order);
    }

    @Override
    public Optional<Order> getOrder(Long id) {
        return orderRepository.findOrderById(id);
    }
}
