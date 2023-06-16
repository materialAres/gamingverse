package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.GamePhotoDto;
import it.shine.gamingverse.exceptions.isnull.GamePhotoDtoNullException;
import it.shine.gamingverse.exceptions.listempty.GamePhotoListEmptyException;
import it.shine.gamingverse.exceptions.notfound.GamePhotoNotFoundException;
import it.shine.gamingverse.services.GamePhotoServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/game-photos")
public class GamePhotoController {

    @Autowired
    private GamePhotoServiceImpl gamePhotoService;

    @PostMapping("/create")
    public ResponseEntity<?> addGamePhoto(@RequestBody GamePhotoDto gamePhotoDto) {
        try {
            GamePhotoDto savedGamePhotoDto = gamePhotoService.addGamePhoto(gamePhotoDto);

            return new ResponseEntity<>(savedGamePhotoDto, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GamePhotoDto> getGamePhotoById(@PathVariable("id") Integer id) {
        try {
            GamePhotoDto gamePhotoDto = gamePhotoService.getGamePhotoById(id);

            return new ResponseEntity<>(gamePhotoDto, HttpStatus.OK);
        } catch (GamePhotoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<GamePhotoDto>> getAllGamePhotos() {
        try {
            return new ResponseEntity<>(gamePhotoService.getAllGamePhotos(), HttpStatus.OK);
        } catch (GamePhotoListEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<GamePhotoDto> updateGamePhoto(@PathVariable("id") Integer id,
                                                        @RequestBody GamePhotoDto gamePhotoDto) {
        try {
            gamePhotoDto.setId(id);

            GamePhotoDto updatedGamePhotoDto = gamePhotoService.updateGamePhoto(gamePhotoDto);

            return new ResponseEntity<>(updatedGamePhotoDto, HttpStatus.OK);
        } catch (GamePhotoDtoNullException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteGamePhoto(@PathVariable("id") Integer id) {
        try {
            gamePhotoService.deleteGamePhoto(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (GamePhotoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}

