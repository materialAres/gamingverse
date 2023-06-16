package it.shine.gamingverse.exceptions.isnull;

public class GamePhotoDtoNullException extends Exception {

    public GamePhotoDtoNullException() {
        super("GamePhoto dto is null or empty");
    }

}
