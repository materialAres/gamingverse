package it.shine.gamingverse.exceptions;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException() {
        super("Customer not found");
    }
}
