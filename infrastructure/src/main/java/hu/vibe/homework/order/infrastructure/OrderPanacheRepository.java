package hu.vibe.homework.order.infrastructure;

import hu.vibe.homework.order.domain.Order;
import hu.vibe.homework.order.domain.OrderRepository;
import hu.vibe.homework.order.infrastructure.entity.OrderEntity;
import hu.vibe.homework.order.infrastructure.entity.OrderMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderPanacheRepository implements PanacheRepository<OrderEntity>, OrderRepository {

    private final EntityManager em;
    private final OrderMapper orderMapper;

    @Override
    public Optional<Order> findOrderById(UUID id) {
        // PanacheRepository should support findByIdOptional(UUID), but if not, use
        // find().firstResultOptional()
        Optional<OrderEntity> entityOpt = find("id", id).firstResultOptional();
        return entityOpt.map(orderMapper::toDomain);
    }

    @Override
    @Transactional
    public Order save(Order order) {
        OrderEntity entity = orderMapper.toEntity(order);
        if (entity.getId() == null) {
            persist(entity);
            return orderMapper.toDomain(entity);
        } else {
            OrderEntity merged = em.merge(entity);
            return orderMapper.toDomain(merged);
        }
    }
}
