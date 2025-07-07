package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.Order;
import hu.vibe.homework.hello.domain.OrderItem;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(
        componentModel = "jakarta",
        uses = {OrderItemMapper.class, AddressMapper.class})
public interface OrderMapper {
    OrderMapper INSTANCE = Mappers.getMapper(OrderMapper.class);

    default Order toDomain(OrderEntity entity) {
        if (entity == null) return null;
        var itemMapper = OrderItemMapper.INSTANCE;
        var addressMapper = AddressMapper.INSTANCE;
        var items = entity.getItems() == null
                ? List.<OrderItem>of()
                : new java.util.ArrayList<OrderItem>(
                        entity.getItems().stream().map(itemMapper::toDomain).toList());
        var shipping = addressMapper.toDomain(entity.getShippingAddress());
        var billing = addressMapper.toDomain(entity.getBillingAddress());
        return new Order(
                entity.getId(),
                entity.getCustomerId(),
                entity.getCreatedAt(),
                entity.getTotalPrice(),
                items,
                entity.getStatus(),
                shipping,
                billing);
    }

    default OrderEntity toEntity(Order domain) {
        if (domain == null) return null;
        var itemMapper = OrderItemMapper.INSTANCE;
        var addressMapper = AddressMapper.INSTANCE;
        OrderEntity entity = new OrderEntity();
        entity.setId(domain.id());
        entity.setCustomerId(domain.customerId());
        entity.setCreatedAt(domain.createdAt());
        entity.setTotalPrice(domain.totalPrice());
        entity.setItems(
                domain.items() == null
                        ? List.<OrderItemEntity>of()
                        : new java.util.ArrayList<OrderItemEntity>(domain.items().stream()
                                .map(itemMapper::toEntity)
                                .toList()));
        entity.setStatus(domain.status());
        entity.setShippingAddress(addressMapper.toEntity(domain.shippingAddress()));
        entity.setBillingAddress(addressMapper.toEntity(domain.billingAddress()));
        return entity;
    }
}
