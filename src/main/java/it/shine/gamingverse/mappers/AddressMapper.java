package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.AddressDto;
import it.shine.gamingverse.entities.Address;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    AddressMapper INSTANCE = Mappers.getMapper(AddressMapper.class);

    @Mapping(source = "customer.id", target = "customerId")
    AddressDto addressToAddressDto(Address address);

    @Mapping(source = "customerId", target = "customer.id")
    Address addressDtoToAddress(AddressDto addressDto);

}