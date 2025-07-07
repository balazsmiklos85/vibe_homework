package hu.vibe.homework.hello.infrastructure;

import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderRepository;
import hu.vibe.homework.hello.infrastructure.entity.OrderEntity;
import hu.vibe.homework.hello.infrastructure.entity.OrderMapper;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import java.util.Optional;
import lombok.RequiredArgsConstructor;

@ApplicationScoped
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderPanacheRepository implements PanacheRepository<OrderEntity>, OrderRepository {

    private final OrderMapper orderMapper;

    @Override
    public Optional<Order> findOrderById(java.util.UUID id) {
        // PanacheRepository should support findByIdOptional(UUID), but if not, use
        // find().firstResultOptional()
        Optional<OrderEntity> entityOpt = find("id", id).firstResultOptional();
        return entityOpt.map(orderMapper::toDomain);
    }

    @jakarta.inject.Inject
    jakarta.persistence.EntityManager em;

    @Override
    @Transactional
    public Order save(Order order) {
        var entity = orderMapper.toEntity(order);
        if (entity.getId() == null) {
            persist(entity);
            return orderMapper.toDomain(entity);
        } else {
            OrderEntity merged = em.merge(entity);
            return orderMapper.toDomain(merged);
        }
    }
}
