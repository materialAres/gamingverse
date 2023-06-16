package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.exceptions.listempty.GameListEmptyException;
import it.shine.gamingverse.exceptions.isnull.GameDtoNullException;

import java.util.List;

public interface GameService {

    GameDto addGame(GameDto game) throws GameDtoNullException;

    GameDto getGameById(Integer id) throws Exception;

    List<GameDto> getAllGames() throws GameListEmptyException;

    GameDto updateGame(GameDto game, Integer id) throws Exception;

    void deleteGame(Integer id) throws Exception;

}

