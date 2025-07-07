package hu.vibe.homework.order.domain;

public record Address(
        String name,
        String city,
        String streetAddress,
        String additionalStreetAddress,
        String country,
        String state,
        String zipCode) {}
