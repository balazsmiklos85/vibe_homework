package hu.vibe.homework.hello.domain;

public record Address(
        String name,
        String city,
        String streetAddress,
        String additionalStreetAddress,
        String country,
        String state,
        String zipCode) {}
