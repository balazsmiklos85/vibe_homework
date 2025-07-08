package hu.vibe.homework.order.infrastructure;

import hu.vibe.homework.order.domain.CreateOrderUseCase;
import hu.vibe.homework.order.domain.GetOrderUseCase;
import hu.vibe.homework.order.domain.UpdateOrderStatusUseCase;
import hu.vibe.homework.order.domain.UpdateShippingAddressUseCase;
import hu.vibe.homework.order.infrastructure.dto.AddressRequestMapper;
import hu.vibe.homework.order.infrastructure.dto.CreateOrderRequest;
import hu.vibe.homework.order.infrastructure.dto.UpdateOrderStatusRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

@Path("/orders")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderResource {

    private final CreateOrderUseCase createOrderUseCase;
    private final GetOrderUseCase getOrderUseCase;
    private final UpdateShippingAddressUseCase updateShippingAddressUseCase;
    private final UpdateOrderStatusUseCase updateOrderStatusUseCase;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(CreateOrderRequest request) {
        // Manual mapping from CreateOrderRequest to CreateOrderCommand
        var items = request.items().stream()
                .map(i -> new hu.vibe.homework.order.domain.OrderItem(i.productCode(), i.quantity(), i.unitPrice()))
                .toList();
        var status = request.status() != null
                ? hu.vibe.homework.order.domain.OrderStatus.valueOf(request.status())
                : hu.vibe.homework.order.domain.OrderStatus.CART;
        var shipping = AddressRequestMapper.toDomain(request.shippingAddress());
        var billing = AddressRequestMapper.toDomain(request.billingAddress());
        var command = new hu.vibe.homework.order.domain.CreateOrderUseCase.CreateOrderCommand(
                items, request.customerId(), status, shipping, billing);
        var order = createOrderUseCase.createOrder(command);
        return Response.status(Response.Status.CREATED).entity(order.id()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") String id) {
        java.util.UUID uuid = java.util.UUID.fromString(id);
        return getOrderUseCase
                .getOrder(uuid)
                .map(order -> Response.ok(order).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }

    @PATCH
    @Path("/{id}/shipping-address")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateShippingAddress(
            @PathParam("id") String id,
            hu.vibe.homework.order.infrastructure.dto.UpdateShippingAddressRequest request) {
        java.util.UUID uuid = java.util.UUID.fromString(id);
        var addressDto = request.shippingAddress();
        var address = AddressRequestMapper.toDomain(addressDto);
        try {
            updateShippingAddressUseCase.updateShippingAddress(uuid, address);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        } catch (IllegalStateException e) {
            return Response.status(Response.Status.CONFLICT)
                    .entity(e.getMessage())
                    .build();
        }
    }

    @PATCH
    @Path("/{id}/status")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateOrderStatus(
            @PathParam("id") String id,
            UpdateOrderStatusRequest request) {
        java.util.UUID uuid = java.util.UUID.fromString(id);
        var status = hu.vibe.homework.order.domain.OrderStatus.valueOf(request.status());
        try {
            updateOrderStatusUseCase.updateOrderStatus(uuid, status);
            return Response.ok().build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity(e.getMessage())
                    .build();
        }
    }
}
