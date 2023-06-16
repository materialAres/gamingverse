package it.shine.gamingverse.exceptions.listempty;

public class GameListEmptyException extends Exception {

    public GameListEmptyException() {
        super("Game list is empty");
    }

}
