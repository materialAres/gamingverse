package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.entities.ConsolePhoto;
import it.shine.gamingverse.exceptions.listempty.ConsolePhotoListEmptyException;
import it.shine.gamingverse.exceptions.isnull.ConsolePhotoDtoNullException;
import it.shine.gamingverse.exceptions.notfound.ConsolePhotoNotFoundException;
import it.shine.gamingverse.mappers.ConsolePhotoMapper;
import it.shine.gamingverse.repositories.ConsolePhotoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
    public ConsolePhotoDto addConsolePhoto(ConsolePhotoDto consolePhotoDto) throws ConstraintViolationException, ConsolePhotoDtoNullException {
        if (ObjectUtils.isEmpty(consolePhotoDto)) {
            throw new ConsolePhotoDtoNullException();
        }

        ConsolePhoto consolePhoto = consolePhotoMapper.consolePhotoDtoToConsolePhoto(consolePhotoDto, entityManager);

        Set<ConstraintViolation<ConsolePhotoDto>> violations = validator.validate(consolePhotoDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        consolePhotoRepository.save(consolePhoto);

        return consolePhotoDto;
    }

    @Override
    public ConsolePhotoDto getConsolePhotoById(Integer id) throws ConsolePhotoNotFoundException {
        ConsolePhoto consolePhoto = consolePhotoRepository.findById(id)
                .orElseThrow(ConsolePhotoNotFoundException::new);

        return consolePhotoMapper.consolePhotoToConsolePhotoDto(consolePhoto);
    }

    @Override
    public List<ConsolePhotoDto> getAllConsolePhotos() throws ConsolePhotoListEmptyException {
        List<ConsolePhoto> consolePhotos = consolePhotoRepository.findAll();

        if (consolePhotos.isEmpty()) {
            throw new ConsolePhotoListEmptyException();
        }

        return consolePhotos
                .stream()
                .map(consolePhotoMapper::consolePhotoToConsolePhotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public ConsolePhotoDto updateConsolePhoto(ConsolePhotoDto consolePhotoDto)
                            throws ConsolePhotoDtoNullException {
        if (ObjectUtils.isEmpty(consolePhotoDto)) {
            throw new ConsolePhotoDtoNullException();
        }

        Set<ConstraintViolation<ConsolePhotoDto>> violations = validator.validate(consolePhotoDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        ConsolePhoto consolePhoto = consolePhotoMapper.consolePhotoDtoToConsolePhoto(consolePhotoDto, entityManager);

        consolePhotoRepository.save(consolePhoto);

        return consolePhotoMapper.consolePhotoToConsolePhotoDto(consolePhoto);
    }

    @Override
    public void deleteConsolePhoto(Integer id) throws ConsolePhotoNotFoundException {
        if (!consolePhotoRepository.existsById(id)) {
            throw new ConsolePhotoNotFoundException();
        }

        consolePhotoRepository.deleteById(id);
    }

}
