package hu.vibe.homework.order.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hu.vibe.homework.order.domain.*;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UpdateShippingAddressServiceTest {
    private OrderRepository orderRepository;
    private UpdateShippingAddressService service;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        service = new UpdateShippingAddressService(orderRepository);
    }

    @Test
    void updateShippingAddress_updatesAndSaves_whenStatusAllowed() {
        UUID id = UUID.randomUUID();
        Address newAddress = mock(Address.class);
        Order order = mock(Order.class);
        when(orderRepository.findOrderById(id)).thenReturn(Optional.of(order));
        when(order.status()).thenReturn(OrderStatus.ORDERED);
        when(orderRepository.save(any())).thenReturn(order);
        Order result = service.updateShippingAddress(id, newAddress);
        assertEquals(order, result);
        verify(orderRepository).save(any(Order.class));
    }

    @Test
    void updateShippingAddress_throwsIllegalArgument_whenOrderNotFound() {
        UUID id = UUID.randomUUID();
        Address newAddress = mock(Address.class);
        when(orderRepository.findOrderById(id)).thenReturn(Optional.empty());
        assertThrows(IllegalArgumentException.class, () -> service.updateShippingAddress(id, newAddress));
    }

    @Test
    void updateShippingAddress_throwsIllegalState_whenStatusForbidden() {
        UUID id = UUID.randomUUID();
        Address newAddress = mock(Address.class);
        Order order = mock(Order.class);
        when(orderRepository.findOrderById(id)).thenReturn(Optional.of(order));
        when(order.status()).thenReturn(OrderStatus.SHIPPED);
        assertThrows(IllegalStateException.class, () -> service.updateShippingAddress(id, newAddress));
    }
}
