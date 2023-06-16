package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.exceptions.listempty.ConsoleListEmptyException;
import it.shine.gamingverse.exceptions.isnull.ConsoleDtoNullException;
import it.shine.gamingverse.exceptions.notfound.ConsoleNotFoundException;
import it.shine.gamingverse.services.ConsoleServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/consoles")
public class ConsoleController {

    @Autowired
    ConsoleServiceImpl consoleService;

    @PostMapping("/add")
    public ResponseEntity<?> addConsole(@RequestBody ConsoleDto consoleDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(consoleService.addConsole(consoleDto));
        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
        } catch (ConsoleDtoNullException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ConsoleDto> getConsoleById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(consoleService.getConsoleById(id));
        } catch (ConsoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<ConsoleDto>> getAllConsoles() {
        try {
            return ResponseEntity.ok(consoleService.getAllConsoles());
        } catch (ConsoleListEmptyException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ConsoleDto> updateConsole(@PathVariable("id") Integer id, @RequestBody ConsoleDto consoleDto) {
        try {
            return ResponseEntity.ok(consoleService.updateConsole(id, consoleDto));
        } catch (ConsoleDtoNullException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteConsole(@PathVariable("id") Integer id) {
        try {
            consoleService.deleteConsole(id);

            return ResponseEntity.noContent().build();
        } catch (ConsoleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
