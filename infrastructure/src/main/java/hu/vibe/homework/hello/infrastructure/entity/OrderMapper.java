package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.Order;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface OrderMapper {
    Order toDomain(OrderEntity entity);
    OrderEntity toEntity(Order domain);
}
