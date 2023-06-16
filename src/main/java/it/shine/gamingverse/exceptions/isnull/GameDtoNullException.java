package it.shine.gamingverse.exceptions.isnull;

public class GameDtoNullException extends Exception {

    public GameDtoNullException() {
        super("Game dto is null or empty");
    }

}
