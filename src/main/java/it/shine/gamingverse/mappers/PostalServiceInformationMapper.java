package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.PostalServiceInformationDto;
import it.shine.gamingverse.entities.PostalServiceInformation;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface PostalServiceInformationMapper {

    @Mappings({
            @Mapping(source = "postalServiceInformationDto.postalServiceCompany", target = "postalServiceCompany"),
            @Mapping(source = "postalServiceInformationDto.serviceType", target = "serviceType"),
            @Mapping(source = "postalServiceInformationDto.deliveryStatus", target = "deliveryStatus")
    })
    PostalServiceInformation postalServiceInformationDtoToPostalServiceInformation(
            PostalServiceInformationDto postalServiceInformationDto
    );

    @Mappings({
            @Mapping(source = "postalServiceCompany", target = "postalServiceCompany"),
            @Mapping(source = "serviceType", target = "serviceType"),
            @Mapping(source = "deliveryStatus", target = "deliveryStatus")
    })
    PostalServiceInformationDto postalServiceInformationToPostalServiceInformationDto(
            PostalServiceInformation postalServiceInformation
    );

}
