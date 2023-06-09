package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.entities.ConsolePhoto;
import it.shine.gamingverse.exceptions.ConsolePhotoNotFoundException;
import it.shine.gamingverse.mappers.ConsolePhotoMapper;
import it.shine.gamingverse.repositories.ConsolePhotoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ConsolePhotoServiceImpl implements ConsolePhotoService {

    @Autowired
    private ConsolePhotoRepository consolePhotoRepository;

    @Autowired
    private ConsolePhotoMapper consolePhotoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Validator validator;

    @Override
    public ConsolePhotoDto addConsolePhoto(ConsolePhotoDto consolePhotoDto) throws Exception {
        ConsolePhoto consolePhoto = consolePhotoMapper.consolePhotoDtoToConsolePhoto(consolePhotoDto, entityManager);

        Set<ConstraintViolation<ConsolePhotoDto>> violations = validator.validate(consolePhotoDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        consolePhotoRepository.save(consolePhoto);

        return consolePhotoDto;
    }

    @Override
    public ConsolePhotoDto getConsolePhotoById(Integer id) throws Exception {
        ConsolePhoto consolePhoto = consolePhotoRepository.findById(id)
                .orElseThrow(ConsolePhotoNotFoundException::new);

        return consolePhotoMapper.consolePhotoToConsolePhotoDto(consolePhoto);
    }

    @Override
    public List<ConsolePhotoDto> getAllConsolePhotos() {
        List<ConsolePhoto> consolePhotos = consolePhotoRepository.findAll();

        return consolePhotos
                .stream()
                .map(consolePhotoMapper::consolePhotoToConsolePhotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsolePhotoDto updateConsolePhoto(ConsolePhotoDto consolePhotoDto) throws Exception {
        ConsolePhoto existingConsolePhoto = consolePhotoRepository
                .findById(consolePhotoDto.getId())
                .orElseThrow(ConsolePhotoNotFoundException::new);

        Set<ConstraintViolation<ConsolePhotoDto>> violations = validator.validate(consolePhotoDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        ConsolePhoto consolePhoto = consolePhotoMapper.consolePhotoDtoToConsolePhoto(consolePhotoDto, entityManager);

        consolePhoto.setId(existingConsolePhoto.getId());

        consolePhoto = consolePhotoRepository.save(consolePhoto);

        return consolePhotoMapper.consolePhotoToConsolePhotoDto(consolePhoto);
    }

    @Override
    public void deleteConsolePhoto(Integer id) throws Exception {
        if (!consolePhotoRepository.existsById(id)) {
            throw new ConsolePhotoNotFoundException();
        }

        consolePhotoRepository.deleteById(id);
    }

}
