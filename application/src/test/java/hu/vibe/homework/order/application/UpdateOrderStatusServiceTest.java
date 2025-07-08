package hu.vibe.homework.order.application;

import hu.vibe.homework.order.domain.Order;
import hu.vibe.homework.order.domain.OrderRepository;
import hu.vibe.homework.order.domain.OrderStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

class UpdateOrderStatusServiceTest {

    private OrderRepository orderRepository;
    private UpdateOrderStatusService service;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        service = new UpdateOrderStatusService(orderRepository);
    }

    @Test
    void updateOrderStatus_whenOrderExists_updatesAndSavesOrder() {
        // Given
        UUID orderId = UUID.randomUUID();
        Order existingOrder = new Order(orderId, UUID.randomUUID(), null, null, null, OrderStatus.ORDERED, null, null);
        when(orderRepository.findOrderById(orderId)).thenReturn(Optional.of(existingOrder));

        // When
        service.updateOrderStatus(orderId, OrderStatus.PAID);

        // Then
        ArgumentCaptor<Order> orderCaptor = ArgumentCaptor.forClass(Order.class);
        verify(orderRepository).save(orderCaptor.capture());
        Order savedOrder = orderCaptor.getValue();
        assertEquals(OrderStatus.PAID, savedOrder.status());
    }

    @Test
    void updateOrderStatus_whenOrderNotFound_throwsException() {
        // Given
        UUID orderId = UUID.randomUUID();
        when(orderRepository.findOrderById(orderId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(IllegalArgumentException.class, () -> {
            service.updateOrderStatus(orderId, OrderStatus.PAID);
        });
    }
}
