package hu.vibe.homework.order.infrastructure;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import hu.vibe.homework.order.domain.*;
import hu.vibe.homework.order.infrastructure.dto.*;
import jakarta.ws.rs.core.Response;
import java.util.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrderResourceTest {
    private CreateOrderUseCase createOrderUseCase;
    private GetOrderUseCase getOrderUseCase;
    private UpdateShippingAddressUseCase updateShippingAddressUseCase;
    private UpdateOrderStatusUseCase updateOrderStatusUseCase;
    private OrderResource resource;

    @BeforeEach
    void setup() {
        createOrderUseCase = mock(CreateOrderUseCase.class);
        getOrderUseCase = mock(GetOrderUseCase.class);
        updateShippingAddressUseCase = mock(UpdateShippingAddressUseCase.class);
        updateOrderStatusUseCase = mock(UpdateOrderStatusUseCase.class);
        resource = new OrderResource(createOrderUseCase, getOrderUseCase, updateShippingAddressUseCase, updateOrderStatusUseCase);
    }

    @Test
    void createOrder_returnsCreatedId() {
        var dto = mock(CreateOrderRequest.class);
        var items = List.of(mock(OrderItemRequest.class));
        var shipping = mock(AddressRequest.class);
        var billing = mock(AddressRequest.class);
        UUID customerId = UUID.randomUUID();
        when(dto.items()).thenReturn(items);
        when(dto.shippingAddress()).thenReturn(shipping);
        when(dto.billingAddress()).thenReturn(billing);
        when(dto.customerId()).thenReturn(customerId);

        var order = mock(Order.class);
        when(order.id()).thenReturn(UUID.randomUUID());
        when(createOrderUseCase.createOrder(any())).thenReturn(order);
        Response resp = resource.createOrder(dto);
        assertEquals(Response.Status.CREATED.getStatusCode(), resp.getStatus());
        assertNotNull(resp.getEntity());
    }

    @Test
    void getOrder_returnsOk_whenFound() {
        UUID id = UUID.randomUUID();
        var order = mock(Order.class);
        when(getOrderUseCase.getOrder(id)).thenReturn(Optional.of(order));
        Response resp = resource.getOrder(id.toString());
        assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());
        assertEquals(order, resp.getEntity());
    }

    @Test
    void getOrder_returnsNotFound_whenMissing() {
        UUID id = UUID.randomUUID();
        when(getOrderUseCase.getOrder(id)).thenReturn(Optional.empty());
        Response resp = resource.getOrder(id.toString());
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp.getStatus());
    }

    @Test
    void updateShippingAddress_returnsOk_onSuccess() {
        UUID id = UUID.randomUUID();
        var req = mock(UpdateShippingAddressRequest.class);
        var addressDto = mock(AddressRequest.class);
        when(req.shippingAddress()).thenReturn(addressDto);
        doReturn(mock(Order.class)).when(updateShippingAddressUseCase).updateShippingAddress(any(), any());
        Response resp = resource.updateShippingAddress(id.toString(), req);
        assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());
    }

    @Test
    void updateShippingAddress_returnsNotFound_onMissingOrder() {
        UUID id = UUID.randomUUID();
        var req = mock(UpdateShippingAddressRequest.class);
        var addressDto = mock(AddressRequest.class);
        when(req.shippingAddress()).thenReturn(addressDto);
        doThrow(new IllegalArgumentException("Order not found"))
                .when(updateShippingAddressUseCase)
                .updateShippingAddress(any(), any());
        Response resp = resource.updateShippingAddress(id.toString(), req);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp.getStatus());
    }

    @Test
    void updateShippingAddress_returnsConflict_onInvalidState() {
        UUID id = UUID.randomUUID();
        var req = mock(UpdateShippingAddressRequest.class);
        var addressDto = mock(AddressRequest.class);
        when(req.shippingAddress()).thenReturn(addressDto);
        doThrow(new IllegalStateException("Invalid state"))
                .when(updateShippingAddressUseCase)
                .updateShippingAddress(any(), any());
        Response resp = resource.updateShippingAddress(id.toString(), req);
        assertEquals(Response.Status.CONFLICT.getStatusCode(), resp.getStatus());
    }

    @Test
    void updateOrderStatus_returnsOk_onSuccess() {
        UUID id = UUID.randomUUID();
        var req = new UpdateOrderStatusRequest("PAID");
        doNothing().when(updateOrderStatusUseCase).updateOrderStatus(id, OrderStatus.PAID);
        Response resp = resource.updateOrderStatus(id.toString(), req);
        assertEquals(Response.Status.OK.getStatusCode(), resp.getStatus());
    }

    @Test
    void updateOrderStatus_returnsNotFound_onMissingOrder() {
        UUID id = UUID.randomUUID();
        var req = new UpdateOrderStatusRequest("PAID");
        doThrow(new IllegalArgumentException("Order not found"))
                .when(updateOrderStatusUseCase)
                .updateOrderStatus(id, OrderStatus.PAID);
        Response resp = resource.updateOrderStatus(id.toString(), req);
        assertEquals(Response.Status.NOT_FOUND.getStatusCode(), resp.getStatus());
    }
}
