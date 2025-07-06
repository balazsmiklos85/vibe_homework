package hu.vibe.homework.hello.infrastructure;

import java.util.Optional;

import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderRepository;
import hu.vibe.homework.hello.infrastructure.entity.OrderEntity;
import hu.vibe.homework.hello.infrastructure.entity.OrderMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderPanacheRepository implements PanacheRepository<OrderEntity>, OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public Optional<Order> findOrderById(java.util.UUID id) {
        // PanacheRepository should support findByIdOptional(UUID), but if not, use find().firstResultOptional()
        Optional<OrderEntity> entityOpt = find("id", id).firstResultOptional();
        return entityOpt.map(orderMapper::toDomain);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        var entity = orderMapper.toEntity(order);
        persist(entity);
        return orderMapper.toDomain(entity);
    }
}
