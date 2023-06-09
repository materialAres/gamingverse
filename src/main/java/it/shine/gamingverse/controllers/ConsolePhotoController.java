package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.exceptions.listempty.ConsolePhotoListEmptyException;
import it.shine.gamingverse.exceptions.isnull.ConsolePhotoDtoNullException;
import it.shine.gamingverse.exceptions.notfound.ConsolePhotoNotFoundException;
import it.shine.gamingverse.services.ConsolePhotoServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/console-photos")
public class ConsolePhotoController {

    @Autowired
    private ConsolePhotoServiceImpl consolePhotoService;

    @PostMapping("/create")
    public ResponseEntity<?> addConsolePhoto(@RequestBody ConsolePhotoDto consolePhotoDto) {
        try {
            ConsolePhotoDto savedConsolePhotoDto = consolePhotoService.addConsolePhoto(consolePhotoDto);

            return new ResponseEntity<>(savedConsolePhotoDto, HttpStatus.CREATED);
        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } catch (ConsolePhotoDtoNullException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsolePhotoDto> getConsolePhoto(@PathVariable("id") Integer id) {
        try {
            ConsolePhotoDto consolePhotoDto = consolePhotoService.getConsolePhotoById(id);

            return new ResponseEntity<>(consolePhotoDto, HttpStatus.OK);
        } catch (ConsolePhotoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsolePhotoDto>> getAllConsolePhotos() {
        try {
            return new ResponseEntity<>(consolePhotoService.getAllConsolePhotos(), HttpStatus.OK);
        } catch (ConsolePhotoListEmptyException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsolePhotoDto> updateConsolePhoto(@PathVariable("id") Integer id,
                                                        @RequestBody ConsolePhotoDto consolePhotoDto) {
        try {
            consolePhotoDto.setId(id);
            ConsolePhotoDto updatedConsolePhotoDto = consolePhotoService.updateConsolePhoto(consolePhotoDto);

            return new ResponseEntity<>(updatedConsolePhotoDto, HttpStatus.OK);
        } catch (ConsolePhotoDtoNullException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteConsolePhoto(@PathVariable("id") Integer id) {
        try {
            consolePhotoService.deleteConsolePhoto(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ConsolePhotoNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
