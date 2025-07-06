package hu.vibe.homework.hello.infrastructure;

import hu.vibe.homework.hello.domain.OrderPort;
import hu.vibe.homework.hello.infrastructure.dto.CreateOrderRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import lombok.RequiredArgsConstructor;



@Path("/orders")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderResource {

    private final OrderPort orderPort;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(CreateOrderRequest request) {
        // Manual mapping from CreateOrderRequest to CreateOrderCommand
        var items = request.items().stream()
            .map(i -> new hu.vibe.homework.hello.domain.OrderItem(i.productCode(), i.quantity(), i.unitPrice()))
            .toList();
        var status = request.status() != null ? hu.vibe.homework.hello.domain.OrderStatus.valueOf(request.status()) : hu.vibe.homework.hello.domain.OrderStatus.CART;
        var shipping = new hu.vibe.homework.hello.domain.Address(
            request.shippingAddress().name(),
            request.shippingAddress().city(),
            request.shippingAddress().streetAddress(),
            request.shippingAddress().additionalStreetAddress(),
            request.shippingAddress().country(),
            request.shippingAddress().state(),
            request.shippingAddress().zipCode()
        );
        var billing = new hu.vibe.homework.hello.domain.Address(
            request.billingAddress().name(),
            request.billingAddress().city(),
            request.billingAddress().streetAddress(),
            request.billingAddress().additionalStreetAddress(),
            request.billingAddress().country(),
            request.billingAddress().state(),
            request.billingAddress().zipCode()
        );
        var command = new hu.vibe.homework.hello.domain.OrderPort.CreateOrderCommand(items, request.customerId(), status, shipping, billing);
        var order = orderPort.createOrder(command);
        return Response.status(Response.Status.CREATED).entity(order.id()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") String id) {
        java.util.UUID uuid = java.util.UUID.fromString(id);
        return orderPort.getOrder(uuid)
                .map(order -> Response.ok(order).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
