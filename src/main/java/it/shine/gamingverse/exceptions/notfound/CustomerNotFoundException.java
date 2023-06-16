package it.shine.gamingverse.exceptions.notfound;

public class CustomerNotFoundException extends Exception {
    public CustomerNotFoundException() {
        super("Customer not found");
    }
}
