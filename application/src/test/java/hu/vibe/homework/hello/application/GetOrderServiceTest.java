package hu.vibe.homework.hello.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hu.vibe.homework.hello.domain.*;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GetOrderServiceTest {
    private OrderRepository orderRepository;
    private GetOrderService service;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        service = new GetOrderService(orderRepository);
    }

    @Test
    void getOrder_returnsOrderIfExists() {
        UUID id = UUID.randomUUID();
        Order order = mock(Order.class);
        when(orderRepository.findOrderById(id)).thenReturn(Optional.of(order));
        Optional<Order> result = service.getOrder(id);
        assertTrue(result.isPresent());
        assertEquals(order, result.get());
    }

    @Test
    void getOrder_returnsEmptyIfNotExists() {
        UUID id = UUID.randomUUID();
        when(orderRepository.findOrderById(id)).thenReturn(Optional.empty());
        Optional<Order> result = service.getOrder(id);
        assertTrue(result.isEmpty());
    }
}
