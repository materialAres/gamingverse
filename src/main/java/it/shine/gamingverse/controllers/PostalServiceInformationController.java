package it.shine.gamingverse.controllers;

import it.shine.gamingverse.controllers.utils.CheckControllerError;
import it.shine.gamingverse.dtos.PostalServiceInformationDto;
import it.shine.gamingverse.exceptions.PostalServiceInformationNotFoundException;
import it.shine.gamingverse.services.PostalServiceInformationServiceImpl;
import jakarta.validation.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/admin/postal-services")
public class PostalServiceInformationController {

    @Autowired
    private PostalServiceInformationServiceImpl postalServiceInformationService;

    @PostMapping("/add")
    public ResponseEntity<?> addPostalServiceInformation(@RequestBody PostalServiceInformationDto postalServiceInformationDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(postalServiceInformationService.addPostalServiceInformation(postalServiceInformationDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostalServiceInformationDto> getPostalServiceInformationById(@PathVariable("id") Integer id) {
        try {
            return ResponseEntity.ok(postalServiceInformationService.getPostalServiceInformationById(id));
        } catch (PostalServiceInformationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("")
    public ResponseEntity<List<PostalServiceInformationDto>> getAllPostalServiceInformation() {
        try {
            return ResponseEntity.ok(postalServiceInformationService.getAllPostalServiceInformation());

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePostalServiceInformation(@PathVariable("id") Integer id, @RequestBody PostalServiceInformationDto postalServiceInformationDto) {
        try {
            return ResponseEntity.ok(postalServiceInformationService.updatePostalServiceInformation(id, postalServiceInformationDto));

        } catch (ConstraintViolationException e) {
            Map<String, String> errors = CheckControllerError.checkControllerErrors(e);

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);

        } catch (PostalServiceInformationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePostalServiceInformation(@PathVariable("id") Integer id) {
        try {
            postalServiceInformationService.deletePostalServiceInformation(id);

            return ResponseEntity.noContent().build();
        } catch (PostalServiceInformationNotFoundException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
