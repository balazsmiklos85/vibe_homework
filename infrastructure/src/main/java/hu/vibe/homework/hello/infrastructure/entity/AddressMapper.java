package hu.vibe.homework.hello.infrastructure.entity;

import hu.vibe.homework.hello.domain.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "jakarta")


public interface AddressMapper {
    AddressEntity toEntity(Address address);
    Address toDomain(AddressEntity entity);
}
