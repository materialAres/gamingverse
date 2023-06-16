package it.shine.gamingverse.exceptions.isnull;

public class ConsolePhotoDtoNullException extends Exception {

    public ConsolePhotoDtoNullException() {
        super("ConsolePhoto dto is null or empty");
    }

}