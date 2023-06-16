package it.shine.gamingverse.exceptions.listempty;

public class AddressListEmptyException extends Exception {

    public AddressListEmptyException() {
        super("Address list is empty");
    }

}
