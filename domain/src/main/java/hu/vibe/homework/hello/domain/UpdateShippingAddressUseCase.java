package hu.vibe.homework.hello.domain;

import java.util.UUID;

public interface UpdateShippingAddressUseCase {
    Order updateShippingAddress(UUID orderId, Address newAddress);
}
