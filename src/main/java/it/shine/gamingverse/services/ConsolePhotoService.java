package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsolePhotoDto;

import java.util.List;

public interface ConsolePhotoService {

    ConsolePhotoDto addConsolePhoto(ConsolePhotoDto consolePhotoDto) throws Exception;

    ConsolePhotoDto getConsolePhotoById(Integer id) throws Exception;

    List<ConsolePhotoDto> getAllConsolePhotos();

    ConsolePhotoDto updateConsolePhoto(ConsolePhotoDto consolePhotoDto) throws Exception;

    void deleteConsolePhoto(Integer id) throws Exception;

}
