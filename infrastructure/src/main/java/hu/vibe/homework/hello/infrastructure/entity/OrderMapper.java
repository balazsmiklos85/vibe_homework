package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.Order;
import org.mapstruct.Mapper;


import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "jakarta", uses = {OrderItemMapper.class, AddressMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    Order toDomain(OrderEntity entity);

    OrderEntity toEntity(Order domain);
}

