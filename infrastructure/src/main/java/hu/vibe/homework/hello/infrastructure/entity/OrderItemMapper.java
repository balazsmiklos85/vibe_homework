package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.OrderItem;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")


public interface OrderItemMapper {
    OrderItemMapper INSTANCE = new OrderItemMapper() {};
    default OrderItemEntity toEntity(OrderItem item) {
        if (item == null) return null;
        OrderItemEntity entity = new OrderItemEntity();
        entity.setProductCode(item.productCode());
        entity.setQuantity(item.quantity());
        entity.setUnitPrice(item.unitPrice());
        return entity;
    }

    default OrderItem toDomain(OrderItemEntity entity) {
        if (entity == null) return null;
        return new OrderItem(entity.getProductCode(), entity.getQuantity(), entity.getUnitPrice());
    }
}
