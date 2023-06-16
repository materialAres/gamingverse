package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.AddressDto;
import it.shine.gamingverse.entities.Address;
import it.shine.gamingverse.exceptions.isnull.AddressDtoNullException;
import it.shine.gamingverse.exceptions.listempty.AddressListEmptyException;
import it.shine.gamingverse.exceptions.notfound.AddressNotFoundException;
import it.shine.gamingverse.exceptions.notfound.CustomerNotFoundException;
import it.shine.gamingverse.mappers.AddressMapper;
import it.shine.gamingverse.repositories.AddressRepository;
import it.shine.gamingverse.repositories.CustomerRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class AddressServiceImpl implements AddressService {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private AddressMapper addressMapper;

    @Autowired
    private Validator validator;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public AddressDto addAddress(AddressDto addressDto) throws AddressDtoNullException {
        if (ObjectUtils.isEmpty(addressDto)) {
            throw new AddressDtoNullException();
        }

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(addressDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Address address = addressMapper.addressDtoToAddress(addressDto);

        addressRepository.save(address);

        return addressMapper.addressToAddressDto(address);
    }

    @Override
    public AddressDto getAddressById(Integer id) throws AddressNotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(AddressNotFoundException::new);

        return addressMapper.addressToAddressDto(address);
    }

    @Override
    public List<AddressDto> getAllAddresses() throws AddressListEmptyException {
        List<Address> addresses = addressRepository.findAll();

        if (ObjectUtils.isEmpty(addresses)) {
            throw new AddressListEmptyException();
        }

        List<AddressDto> addressesDto = new ArrayList<>();

        for (Address address : addresses) {
            addressesDto.add(addressMapper.addressToAddressDto(address));
        }

        return addressesDto;
    }

    @Override
    public AddressDto updateAddress(Integer id, AddressDto addressDto) throws AddressNotFoundException, CustomerNotFoundException, AddressDtoNullException {
        if (ObjectUtils.isEmpty(addressDto)) {
            throw new AddressDtoNullException();
        }

        Set<ConstraintViolation<AddressDto>> violations = validator.validate(addressDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        addressDto.setId(id);
        Address address = addressMapper.addressDtoToAddress(addressDto);

        addressRepository.save(address);

        return addressMapper.addressToAddressDto(address);
    }

    @Override
    public void deleteAddress(Integer id) throws AddressNotFoundException {
        Address address = addressRepository.findById(id)
                .orElseThrow(AddressNotFoundException::new);

        addressRepository.delete(address);
    }

}
