package hu.vibe.homework.order.domain;

import java.util.UUID;

public interface UpdateShippingAddressUseCase {
    Order updateShippingAddress(UUID orderId, Address newAddress);
}
