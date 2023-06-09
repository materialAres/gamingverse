package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.entities.Game;

import java.util.Collection;

import java.util.List;

public interface GameService {

    GameDto addGame(GameDto game);

    GameDto getGameById(Integer id) throws Exception;

    List<GameDto> getAllGames();

    GameDto updateGame(Integer id, GameDto game) throws Exception;

    void deleteGame(Integer id) throws Exception;

}

