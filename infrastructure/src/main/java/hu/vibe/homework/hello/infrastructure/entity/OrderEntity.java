package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.OrderStatus;
import jakarta.persistence.AttributeOverride;
import jakarta.persistence.AttributeOverrides;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.time.Instant;
import java.util.List;
import java.util.UUID;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "ORDERS")
@Getter
@Setter
public class OrderEntity {

    @Id
    @GeneratedValue
    private UUID id;

    private UUID customerId;

    private Instant createdAt;
    private double totalPrice;

    @ElementCollection
    private List<OrderItemEntity> items;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "shipping_name")),
        @AttributeOverride(name = "city", column = @Column(name = "shipping_city")),
        @AttributeOverride(name = "streetAddress", column = @Column(name = "shipping_street_address")),
        @AttributeOverride(
                name = "additionalStreetAddress",
                column = @Column(name = "shipping_additional_street_address")),
        @AttributeOverride(name = "country", column = @Column(name = "shipping_country")),
        @AttributeOverride(name = "state", column = @Column(name = "shipping_state")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "shipping_zip_code"))
    })
    private AddressEntity shippingAddress;

    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "billing_name")),
        @AttributeOverride(name = "city", column = @Column(name = "billing_city")),
        @AttributeOverride(name = "streetAddress", column = @Column(name = "billing_street_address")),
        @AttributeOverride(
                name = "additionalStreetAddress",
                column = @Column(name = "billing_additional_street_address")),
        @AttributeOverride(name = "country", column = @Column(name = "billing_country")),
        @AttributeOverride(name = "state", column = @Column(name = "billing_state")),
        @AttributeOverride(name = "zipCode", column = @Column(name = "billing_zip_code"))
    })
    private AddressEntity billingAddress;
}
