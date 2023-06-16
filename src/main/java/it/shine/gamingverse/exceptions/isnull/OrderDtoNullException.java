package it.shine.gamingverse.exceptions.isnull;

public class OrderDtoNullException extends Exception {

    public OrderDtoNullException() {
        super("Order dto is null or empty");
    }

}
