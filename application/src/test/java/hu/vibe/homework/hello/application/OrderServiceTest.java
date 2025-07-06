package hu.vibe.homework.hello.application;

import hu.vibe.homework.hello.domain.*;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {
    @Test
    void createOrder_setsAllFieldsCorrectly() {
        OrderRepository repo = Mockito.mock(OrderRepository.class);
        OrderService service = new OrderService(repo);

        List<OrderItem> items = List.of(
                new OrderItem("P1", 2, 10.0),
                new OrderItem("P2", 1, 20.0)
        );
        Address shipping = new Address("Alice", "Budapest", "Main St 1", "Apt 2", "Hungary", "Pest", "1234");
        Address billing = new Address("Bob", "Debrecen", "Second St 5", "", "Hungary", "Hajdu", "5678");
        UUID customerId = UUID.randomUUID();
        OrderPort.CreateOrderCommand cmd = new OrderPort.CreateOrderCommand(items, customerId, OrderStatus.ORDERED, shipping, billing);

        Mockito.when(repo.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));

        Order order = service.createOrder(cmd);
        assertNotNull(order);
        assertEquals(40.0, order.totalPrice(), 0.001);
        assertEquals(OrderStatus.ORDERED, order.status());
        assertEquals(2, order.items().size());
        assertEquals("Alice", order.shippingAddress().name());
        assertEquals("Bob", order.billingAddress().name());
        assertNotNull(order.createdAt());
        assertEquals(customerId, order.customerId());
    }

    @Test
    void createOrder_legacyCommand_setsCustomerIdNull() {
        OrderRepository repo = Mockito.mock(OrderRepository.class);
        OrderService service = new OrderService(repo);

        List<OrderItem> items = List.of(new OrderItem("P1", 1, 10.0));
        Address addr = new Address("A", "City", "St", "", "Country", "State", "Zip");
        // Legacy: no customerId provided
        OrderPort.CreateOrderCommand cmd = new OrderPort.CreateOrderCommand(items, null, null, addr, addr);
        Mockito.when(repo.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));
        Order order = service.createOrder(cmd);
        assertNull(order.customerId());
    }

    @Test
    void createOrder_defaultsStatusToCart() {
        OrderRepository repo = Mockito.mock(OrderRepository.class);
        OrderService service = new OrderService(repo);
        List<OrderItem> items = List.of(new OrderItem("P1", 1, 5.0));
        Address addr = new Address("C", "City", "St", "", "Country", "State", "Zip");
        OrderPort.CreateOrderCommand cmd = new OrderPort.CreateOrderCommand(items, null, null, addr, addr);
        Mockito.when(repo.save(Mockito.any())).thenAnswer(inv -> inv.getArgument(0));
        Order order = service.createOrder(cmd);
        assertEquals(OrderStatus.CART, order.status());
    }
}
