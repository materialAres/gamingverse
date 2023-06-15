package it.shine.gamingverse.exceptions;

public class AddressDtoNullException extends Exception {

    public AddressDtoNullException() {
        super("Address dto is null or empty");
    }

}
