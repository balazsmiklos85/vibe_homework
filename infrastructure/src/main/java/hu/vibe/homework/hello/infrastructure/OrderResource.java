package hu.vibe.homework.hello.infrastructure;

import hu.vibe.homework.hello.domain.OrderPort;
import hu.vibe.homework.hello.infrastructure.dto.CreateOrderRequest;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.RequiredArgsConstructor;

import java.net.URI;

@Path("/orders")
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class OrderResource {

    private final OrderPort orderPort;

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createOrder(CreateOrderRequest request) {
        var createdOrder = orderPort.createOrder(request.description());
        return Response.created(URI.create("/orders/" + createdOrder.id())).entity(createdOrder).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrder(@PathParam("id") Long id) {
        return orderPort.getOrder(id)
                .map(order -> Response.ok(order).build())
                .orElse(Response.status(Response.Status.NOT_FOUND).build());
    }
}
