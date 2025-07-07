package hu.vibe.homework.order.application;

import hu.vibe.homework.order.domain.Order;
import hu.vibe.homework.order.domain.OrderRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class GetOrderService implements hu.vibe.homework.order.domain.GetOrderUseCase {
    private final OrderRepository orderRepository;

    @Override
    public Optional<Order> getOrder(UUID id) {
        return orderRepository.findOrderById(id);
    }
}
