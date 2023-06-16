package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.AddressDto;
import it.shine.gamingverse.exceptions.isnull.AddressDtoNullException;
import it.shine.gamingverse.exceptions.listempty.AddressListEmptyException;
import it.shine.gamingverse.exceptions.notfound.AddressNotFoundException;
import it.shine.gamingverse.exceptions.notfound.CustomerNotFoundException;
import it.shine.gamingverse.services.AddressServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/addresses")
public class AddressController {

    @Autowired
    private AddressServiceImpl addressService;

    @PostMapping("/add")
    public ResponseEntity<?> addAddress(@RequestBody AddressDto addressDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(addressService.addAddress(addressDto));
        } catch (AddressDtoNullException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDto> getAddressById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(addressService.getAddressById(id));
        } catch (AddressNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<AddressDto>> getAllAddresses() {
        try {
            return ResponseEntity.ok(addressService.getAllAddresses());
        } catch (AddressListEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAddress(@PathVariable("id") Integer id, @RequestBody AddressDto addressDto) {
        try {
            return ResponseEntity.ok(addressService.updateAddress(id, addressDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (AddressNotFoundException | CustomerNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        } catch (AddressDtoNullException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") Integer id) {
        try {
            addressService.deleteAddress(id);

            return ResponseEntity.noContent().build();
        } catch (AddressNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
