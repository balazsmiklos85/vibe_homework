package hu.vibe.homework.hello.infrastructure.dto;

public record AddressRequest(
        String name,
        String city,
        String streetAddress,
        String additionalStreetAddress,
        String country,
        String state,
        String zipCode) {}
