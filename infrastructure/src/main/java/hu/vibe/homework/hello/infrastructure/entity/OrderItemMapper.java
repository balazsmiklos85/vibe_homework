package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")


public interface OrderItemMapper {
    OrderItemEntity toEntity(OrderItem item);
    OrderItem toDomain(OrderItemEntity entity);
}
