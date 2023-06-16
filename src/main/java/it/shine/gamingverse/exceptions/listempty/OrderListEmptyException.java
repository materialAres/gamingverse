package it.shine.gamingverse.exceptions.listempty;

public class OrderListEmptyException extends Exception {

    public OrderListEmptyException() {
        super("Order list is empty");
    }

}
