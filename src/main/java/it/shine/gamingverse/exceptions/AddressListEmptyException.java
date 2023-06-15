package it.shine.gamingverse.exceptions;

public class AddressListEmptyException extends Exception {

    public AddressListEmptyException() {
        super("Address list is null");
    }
}
