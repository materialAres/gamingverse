package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.AddressDto;
import it.shine.gamingverse.exceptions.AddressDtoNullException;
import it.shine.gamingverse.exceptions.AddressListEmptyException;

import java.util.List;

public interface AddressService {

    AddressDto addAddress(AddressDto address) throws AddressDtoNullException;

    AddressDto getAddressById(Integer id) throws Exception;

    List<AddressDto> getAllAddresses() throws AddressListEmptyException;

    AddressDto updateAddress(Integer id, AddressDto address) throws Exception;

    void deleteAddress(Integer id) throws Exception;

}
