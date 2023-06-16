package it.shine.gamingverse.exceptions.isnull;

public class AddressDtoNullException extends Exception {

    public AddressDtoNullException() {
        super("Address dto is null or empty");
    }

}
