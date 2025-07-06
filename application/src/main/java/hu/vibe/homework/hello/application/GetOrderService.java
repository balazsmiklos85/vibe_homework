package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.Order;

import hu.vibe.homework.hello.domain.OrderRepository;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import lombok.RequiredArgsConstructor;

import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class GetOrderService implements hu.vibe.homework.hello.domain.GetOrderUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrder(UUID id) {
        return orderRepository.findOrderById(id);
    }
}
