package hu.vibe.homework.order.infrastructure;

import static io.restassured.RestAssured.given;

import hu.vibe.homework.order.infrastructure.dto.*;
import io.quarkus.test.junit.QuarkusTest;
import java.math.BigDecimal;
import org.junit.jupiter.api.Test;

@QuarkusTest
class OrderResourceIT {
    @Test
    void createOrder_and_fetchOrder_and_updateShippingAddress() throws Exception {
        var objectMapper = new com.fasterxml.jackson.databind.ObjectMapper();

        // --- Create Order ---
        var item = new OrderItemRequest("PROD-1", 2, new BigDecimal("1000"));
        var shipping = new AddressRequest("Alice", "Budapest", "Fo ut 1", "", "Hungary", "", "1234");
        var billing = new AddressRequest("Bob", "Debrecen", "Baross 2", "", "Hungary", "", "4321");
        var customerId = java.util.UUID.randomUUID();
        var createOrder = new CreateOrderRequest(java.util.List.of(item), customerId, null, shipping, billing);

        // Serialize request body to JSON
        String createOrderJson = objectMapper.writeValueAsString(createOrder);

        // Create
        String orderIdJson = given().contentType("application/json")
                .body(createOrderJson)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .body(org.hamcrest.Matchers.notNullValue())
                .extract()
                .asString();
        String orderId = objectMapper.readValue(orderIdJson, String.class);

        // Fetch
        String fetchResponse = given().when()
                .get("/orders/" + orderId)
                .then()
                .statusCode(200)
                .extract()
                .asString();
        // Parse response JSON
        var fetchNode = objectMapper.readTree(fetchResponse);
        org.junit.jupiter.api.Assertions.assertEquals(
                orderId, fetchNode.get("id").asText());
        org.junit.jupiter.api.Assertions.assertEquals(
                customerId.toString(), fetchNode.get("customerId").asText());

        // Update shipping address
        var newShipping = new AddressRequest("Carol", "Szeged", "Kossuth 3", "", "Hungary", "", "5678");
        var updateReq = new UpdateShippingAddressRequest(newShipping);
        String updateReqJson = objectMapper.writeValueAsString(updateReq);
        given().contentType("application/json")
                .body(updateReqJson)
                .when()
                .patch("/orders/" + orderId + "/shipping-address")
                .then()
                .statusCode(200);
    }
}
