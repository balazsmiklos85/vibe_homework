package hu.vibe.homework.order.infrastructure.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class AddressEntity {
    private String name;
    private String city;
    private String streetAddress;
    private String additionalStreetAddress;
    private String country;
    private String state;
    private String zipCode;
}
