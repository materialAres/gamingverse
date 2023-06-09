package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GamePhotoDto;

import java.util.List;

public interface GamePhotoService {

    GamePhotoDto addGamePhoto(GamePhotoDto gamePhotoDto) throws Exception;

    GamePhotoDto getGamePhotoById(Integer id) throws Exception;

    List<GamePhotoDto> getAllGamePhotos();

    GamePhotoDto updateGamePhoto(GamePhotoDto gamePhotoDto) throws Exception;

    void deleteGamePhoto(Integer id) throws Exception;

}



