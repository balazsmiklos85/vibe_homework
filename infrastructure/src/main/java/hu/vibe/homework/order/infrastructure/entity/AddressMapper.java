package hu.vibe.homework.order.infrastructure.entity;

import hu.vibe.homework.order.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")
public interface AddressMapper {
    AddressMapper INSTANCE = new AddressMapper() {};

    default AddressEntity toEntity(Address address) {
        if (address == null) return null;
        AddressEntity entity = new AddressEntity();
        entity.setName(address.name());
        entity.setCity(address.city());
        entity.setStreetAddress(address.streetAddress());
        entity.setAdditionalStreetAddress(address.additionalStreetAddress());
        entity.setCountry(address.country());
        entity.setState(address.state());
        entity.setZipCode(address.zipCode());
        return entity;
    }

    default Address toDomain(AddressEntity entity) {
        if (entity == null) return null;
        return new Address(
                entity.getName(),
                entity.getCity(),
                entity.getStreetAddress(),
                entity.getAdditionalStreetAddress(),
                entity.getCountry(),
                entity.getState(),
                entity.getZipCode());
    }
}
