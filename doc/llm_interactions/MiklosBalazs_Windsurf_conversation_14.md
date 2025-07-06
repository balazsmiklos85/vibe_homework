# 2025-07-06

## 22:09

> I can create orders, I can fetch them. Add an endpoint that would update the shipping address. Make sure not to update the address if the order status is already in shipping, delivered or canceled.

The plan needs to be updated to reflect the new user request: add an endpoint to update the shipping address of an order, with the constraint that the address should not be updated if the order status is "shipping", "delivered", or "canceled". I will add this as a new task and note.

Updating the plan now.

To add an endpoint for updating the shipping address, here’s what I will do:

Design the API: Add a new endpoint to OrderResource.java (likely a PUT or PATCH to /orders/{id}/shipping-address).
DTO: Define a DTO for the new shipping address payload (if not already present).
Business Logic: In the endpoint, fetch the order, check its status, and only allow the update if it is not shipping, delivered, or canceled.
Mapping: Ensure all mapping and validation occurs in the infrastructure layer, as per your architectural boundaries.
Testing: Add/update tests to cover this endpoint and the status check.
Let me start by checking the structure of OrderResource.java and what DTOs are available, so I can design the endpoint and see what needs to be added.

Updates:

Added notes about the existence of AddressRequest DTO and the OrderStatus enum (with correct values: SHIPPED, DELIVERED, CANCELED).
Updated the note to use the correct status values.
Broadened the task list to include DTO and domain layer changes.
Proceeding to update the plan accordingly.

Update: Added note and checked off the creation of the 
UpdateShippingAddressRequest
/home/bmiklos/projects/vibe_homework/infrastructure/src/main/java/hu/vibe/homework/hello/infrastructure/dto/UpdateShippingAddressRequest.java
 DTO, as the execution agent created this file for the endpoint payload.

Now updating the plan accordingly.

The PATCH endpoint for updating the shipping address has been implemented in the infrastructure layer, including the status restriction logic. The API design step is complete, and the AddressRequest DTO for the update has been verified/created. The next goal is to add domain/application logic for updating the shipping address (if needed) and to ensure the update is persisted correctly.

Updating the plan to check off completed steps and update the current goal.

I have added a new PATCH endpoint /orders/{id}/shipping-address to 
OrderResource
. This endpoint:

Accepts a JSON body matching 
UpdateShippingAddressRequest
/home/bmiklos/projects/vibe_homework/infrastructure/src/main/java/hu/vibe/homework/hello/infrastructure/dto/UpdateShippingAddressRequest.java
 (which contains a new shipping address).
Looks up the order by ID.
Checks the order status, and blocks updates if the status is SHIPPED, DELIVERED, or CANCELED.
Updates the shipping address otherwise, using the correct mapping logic in the infrastructure layer.
Next steps:

If your domain/application layer requires a dedicated update method (not just upsert via 
createOrder
), let me know and I can refactor that as well.
Add or update tests to cover this endpoint.
You can now PATCH to /orders/{id}/shipping-address with a new address payload to update the shipping address, unless the order is already shipped, delivered, or canceled. If you’d like to see a sample request or want tests added, just let me know!

## 22:14

> Upsert is not acceptable at all! That would create multiple orders at every update! Also the order state logic does not belong in the infrastructure layer!

Updating the plan to reflect the user's feedback:

The user clarified that upsert (using createOrder for update) is not acceptable, as it would create multiple orders.
The user also clarified that order state logic (business rules) should not be in the infrastructure layer, but in the domain/application layer.
The next step is to move the update and status logic to the domain/application layer and provide a dedicated update method.
I'll update the plan to capture these important clarifications and adjust the next steps accordingly.

The plan needs updating to reflect the user's feedback:

Upsert via createOrder is not acceptable, as it would create multiple orders.
Order state logic should not be in the infrastructure layer; it belongs in the domain/application layer.
The next step is to design and implement a proper domain/application method for updating the shipping address, including the necessary status checks.
I will update the plan to clarify these requirements and adjust the task list accordingly.

The update shipping address endpoint is now correctly implemented:

The infrastructure layer (
OrderResource
) only maps the DTO to the domain 
Address
 and delegates business logic and status validation to the application/domain layer (OrderPort.updateShippingAddress).
The endpoint now returns appropriate HTTP responses based on exceptions thrown by the domain logic (404 for not found, 409 for invalid state).
Summary of changes:

Added 
updateShippingAddress(UUID, Address)
/home/bmiklos/projects/vibe_homework/infrastructure/src/main/java/hu/vibe/homework/hello/infrastructure/OrderResource.java
 to 
OrderPort
 and implemented it in 
OrderService
 with proper status validation.
Refactored the PATCH endpoint to delegate all logic to the domain layer and handle exceptions as HTTP responses.
This ensures no upsert or duplicate orders, and all business rules are enforced in the correct layer.

If you need tests or further improvements, let me know!

## 22:18

> How is the IllegalStateException handled? Not by returning an HTTP 500, right?

No, the IllegalStateException is not returned as an HTTP 500.

In the current implementation of your 
OrderResource
 endpoint for updating the shipping address, exceptions are explicitly caught and mapped to appropriate HTTP responses:

 try {
    orderPort.updateShippingAddress(uuid, address);
    return Response.ok().build();
} catch (IllegalArgumentException e) {
    return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
} catch (IllegalStateException e) {
    return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
}

try {
    orderPort.updateShippingAddress(uuid, address);
    return Response.ok().build();
} catch (IllegalArgumentException e) {
    return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
} catch (IllegalStateException e) {
    return Response.status(Response.Status.CONFLICT).entity(e.getMessage()).build();
}

