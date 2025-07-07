package hu.vibe.homework.hello.infrastructure.dto;

import hu.vibe.homework.hello.domain.Address;

public class AddressRequestMapper {
    private AddressRequestMapper() {}

    public static Address toDomain(AddressRequest request) {
        if (request == null) return null;
        return new Address(
                request.name(),
                request.city(),
                request.streetAddress(),
                request.additionalStreetAddress(),
                request.country(),
                request.state(),
                request.zipCode());
    }
}
