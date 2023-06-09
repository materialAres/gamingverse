package it.shine.gamingverse.controllers;

import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.services.ConsolePhotoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/console-photos")
public class ConsolePhotoController {

    @Autowired
    private ConsolePhotoServiceImpl consolePhotoService;

    @PostMapping("/create")
    public ResponseEntity<ConsolePhotoDto> addConsolePhoto(@RequestBody ConsolePhotoDto consolePhotoDto) {
        try {
            ConsolePhotoDto savedConsolePhotoDto = consolePhotoService.addConsolePhoto(consolePhotoDto);

            return new ResponseEntity<>(savedConsolePhotoDto, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsolePhotoDto> getConsolePhoto(@PathVariable("id") Integer id) {
        try {
            ConsolePhotoDto consolePhotoDto = consolePhotoService.getConsolePhotoById(id);

            return new ResponseEntity<>(consolePhotoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<ConsolePhotoDto>> getAllConsolePhotos() {
        List<ConsolePhotoDto> consolePhotosDto = consolePhotoService.getAllConsolePhotos();

        return new ResponseEntity<>(consolePhotosDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsolePhotoDto> updateConsolePhoto(@PathVariable("id") Integer id,
                                                        @RequestBody ConsolePhotoDto consolePhotoDto) {
        try {
            consolePhotoDto.setId(id);

            ConsolePhotoDto updatedConsolePhotoDto = consolePhotoService.updateConsolePhoto(consolePhotoDto);

            return new ResponseEntity<>(updatedConsolePhotoDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> deleteConsolePhoto(@PathVariable("id") Integer id) {
        try {
            consolePhotoService.deleteConsolePhoto(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
