package hu.vibe.homework.hello.application;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hu.vibe.homework.hello.domain.*;
import java.util.List;
import java.util.UUID;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CreateOrderServiceTest {
    private OrderRepository orderRepository;
    private CreateOrderService service;

    @BeforeEach
    void setup() {
        orderRepository = mock(OrderRepository.class);
        service = new CreateOrderService(orderRepository);
    }

    @Test
    void createOrder_savesOrderAndReturnsIt() {
        var items = List.of(mock(OrderItem.class));
        var shipping = mock(Address.class);
        var billing = mock(Address.class);
        var cmd = new CreateOrderUseCase.CreateOrderCommand(
                items, UUID.randomUUID(), OrderStatus.ORDERED, shipping, billing);
        var savedOrder = mock(Order.class);
        when(orderRepository.save(any())).thenReturn(savedOrder);
        Order result = service.createOrder(cmd);
        assertEquals(savedOrder, result);
        verify(orderRepository).save(any(Order.class));
    }
}
