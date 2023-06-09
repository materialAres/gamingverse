package it.shine.gamingverse.controllers;

import it.shine.gamingverse.dtos.GamePhotoDto;
import it.shine.gamingverse.services.GamePhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/game-photos")
public class GamePhotoController {

    @Autowired
    private GamePhotoServiceImpl gamePhotoService;

    @PostMapping("/create")
    public ResponseEntity<GamePhotoDto> addGamePhoto(@RequestBody GamePhotoDto gamePhotoDto) {
        try {
            GamePhotoDto savedGamePhotoDto = gamePhotoService.addGamePhoto(gamePhotoDto);

            return new ResponseEntity<>(savedGamePhotoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamePhotoDto> getGamePhoto(@PathVariable("id") Integer id) {
        try {
            GamePhotoDto gamePhotoDto = gamePhotoService.getGamePhotoById(id);

            return new ResponseEntity<>(gamePhotoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<GamePhotoDto>> getAllGamePhotos() {
        List<GamePhotoDto> gamePhotosDto = gamePhotoService.getAllGamePhotos();

        return new ResponseEntity<>(gamePhotosDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GamePhotoDto> updateGamePhoto(@PathVariable("id") Integer id,
                                                        @RequestBody GamePhotoDto gamePhotoDto) {
        try {
            gamePhotoDto.setId(id);

            GamePhotoDto updatedGamePhotoDto = gamePhotoService.updateGamePhoto(gamePhotoDto);

            return new ResponseEntity<>(updatedGamePhotoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteGamePhoto(@PathVariable("id") Integer id) {
        try {
            gamePhotoService.deleteGamePhoto(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

