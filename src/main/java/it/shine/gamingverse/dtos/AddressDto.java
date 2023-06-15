package it.shine.gamingverse.dtos;

import it.shine.gamingverse.entities.Address;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @NoArgsConstructor @AllArgsConstructor
public class AddressDto {

    private Integer id;
    private String street;
    private String number;
    private String city;
    private String state;
    private String country;
    private String postalCode;
    private String additionalInformation;
    private Integer customerId;

    public AddressDto(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.state = address.getState();
        this.country = address.getCountry();
        this.postalCode = address.getPostalCode();
        this.additionalInformation = address.getAdditionalInformation();
        this.customerId = address.getCustomer().getId();
    }

}

