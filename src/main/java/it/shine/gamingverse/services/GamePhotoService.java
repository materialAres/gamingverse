package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GamePhotoDto;
import it.shine.gamingverse.exceptions.listempty.GamePhotoListEmptyException;

import java.util.List;

public interface GamePhotoService {

    GamePhotoDto addGamePhoto(GamePhotoDto gamePhotoDto) throws Exception;

    GamePhotoDto getGamePhotoById(Integer id) throws Exception;

    List<GamePhotoDto> getAllGamePhotos() throws GamePhotoListEmptyException;

    GamePhotoDto updateGamePhoto(GamePhotoDto gamePhotoDto) throws Exception;

    void deleteGamePhoto(Integer id) throws Exception;

}



