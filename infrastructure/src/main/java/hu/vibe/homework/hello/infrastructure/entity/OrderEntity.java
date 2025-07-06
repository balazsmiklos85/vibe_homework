package hu.vibe.homework.hello.infrastructure.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import java.time.Instant;
import java.util.List;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;

@Entity(name = "ORDERS")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue
    private Long id;

    private Long customerId;

    private Instant createdAt;
    private double totalPrice;

    @ElementCollection
    private List<OrderItemEntity> items;

    @Enumerated(EnumType.STRING)
    private hu.vibe.homework.hello.domain.OrderStatus status;

    @jakarta.persistence.Embedded
    @jakarta.persistence.AttributeOverrides({
        @jakarta.persistence.AttributeOverride(name = "name", column = @jakarta.persistence.Column(name = "shipping_name")),
        @jakarta.persistence.AttributeOverride(name = "city", column = @jakarta.persistence.Column(name = "shipping_city")),
        @jakarta.persistence.AttributeOverride(name = "streetAddress", column = @jakarta.persistence.Column(name = "shipping_street_address")),
        @jakarta.persistence.AttributeOverride(name = "additionalStreetAddress", column = @jakarta.persistence.Column(name = "shipping_additional_street_address")),
        @jakarta.persistence.AttributeOverride(name = "country", column = @jakarta.persistence.Column(name = "shipping_country")),
        @jakarta.persistence.AttributeOverride(name = "state", column = @jakarta.persistence.Column(name = "shipping_state")),
        @jakarta.persistence.AttributeOverride(name = "zipCode", column = @jakarta.persistence.Column(name = "shipping_zip_code"))
    })
    private AddressEntity shippingAddress;

    @jakarta.persistence.Embedded
    @jakarta.persistence.AttributeOverrides({
        @jakarta.persistence.AttributeOverride(name = "name", column = @jakarta.persistence.Column(name = "billing_name")),
        @jakarta.persistence.AttributeOverride(name = "city", column = @jakarta.persistence.Column(name = "billing_city")),
        @jakarta.persistence.AttributeOverride(name = "streetAddress", column = @jakarta.persistence.Column(name = "billing_street_address")),
        @jakarta.persistence.AttributeOverride(name = "additionalStreetAddress", column = @jakarta.persistence.Column(name = "billing_additional_street_address")),
        @jakarta.persistence.AttributeOverride(name = "country", column = @jakarta.persistence.Column(name = "billing_country")),
        @jakarta.persistence.AttributeOverride(name = "state", column = @jakarta.persistence.Column(name = "billing_state")),
        @jakarta.persistence.AttributeOverride(name = "zipCode", column = @jakarta.persistence.Column(name = "billing_zip_code"))
    })
    private AddressEntity billingAddress;
}


