package it.shine.gamingverse.exceptions.isnull;

public class ConsoleDtoNullException extends Exception {

    public ConsoleDtoNullException() {
        super("Console dto is null or empty");
    }

}
