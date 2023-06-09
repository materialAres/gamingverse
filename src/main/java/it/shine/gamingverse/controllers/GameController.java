package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.exceptions.GameNotFoundException;
import it.shine.gamingverse.services.GameServiceImpl;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/games")
public class GameController {

    @Autowired
    private GameServiceImpl gameService;

    @PostMapping("/add")
    public ResponseEntity<?> addGame(@RequestBody GameDto gameDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(gameService.addGame(gameDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameDto> getGameById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(gameService.getGameById(id));
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<GameDto>> getAllGames() {
        try {
            return ResponseEntity.ok(gameService.getAllGames());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateGame(@PathVariable("id") Integer id, @RequestBody GameDto gameDto) {
        try {
            return ResponseEntity.ok(gameService.updateGame(id, gameDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteGame(@PathVariable("id") Integer id) {
        try {
            gameService.deleteGame(id);

            return ResponseEntity.noContent().build();
        } catch (GameNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}

