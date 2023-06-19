package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.exceptions.isnull.ConsoleDtoNullException;
import it.shine.gamingverse.exceptions.listempty.ConsoleListEmptyException;
import it.shine.gamingverse.exceptions.notfound.ConsoleNotFoundException;
import it.shine.gamingverse.mappers.ConsoleMapper;
import it.shine.gamingverse.repositories.ConsolePhotoRepository;
import it.shine.gamingverse.repositories.ConsoleRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    @Autowired
    private ConsoleRepository consoleRepository;

    @Autowired
    private ConsolePhotoRepository consolePhotoRepository;

    @Autowired
    private ConsoleMapper consoleMapper;

    @Autowired
    private Validator validator;

    @Override
    public ConsoleDto addConsole(ConsoleDto consoleDto) throws ConsoleDtoNullException {
        if (ObjectUtils.isEmpty(consoleDto)) {
            throw new ConsoleDtoNullException();
        }

        Set<ConstraintViolation<ConsoleDto>> violations = validator.validate(consoleDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Console console = consoleMapper.consoleDtoToConsole(consoleDto);
        console.setPhotos(consolePhotoRepository.findByConsoleId(consoleDto.getId()));

        consoleRepository.save(console);

        ConsoleDto newConsoleDto = consoleMapper.consoleToConsoleDto(console);
        extractPhotoIdsForDto(console, newConsoleDto, consolePhotoRepository);

        return newConsoleDto;
    }

    @Override
    public ConsoleDto getConsoleById(Integer id) throws ConsoleNotFoundException {
        Console console = consoleRepository.findById(id)
                .orElseThrow(ConsoleNotFoundException::new);

        ConsoleDto newConsoleDto = consoleMapper.consoleToConsoleDto(console);
        extractPhotoIdsForDto(console, newConsoleDto, consolePhotoRepository);

        return newConsoleDto;
    }

    @Override
    public List<ConsoleDto> getAllConsoles() throws ConsoleListEmptyException {
        List<Console> consoles = consoleRepository.findAll();

        if (consoles.isEmpty()) {
            throw new ConsoleListEmptyException();
        }

        List<ConsoleDto> consolesDto = new ArrayList<>();

        for (Console console : consoles) {
            ConsoleDto newConsoleDto = consoleMapper.consoleToConsoleDto(console);
            extractPhotoIdsForDto(console, newConsoleDto, consolePhotoRepository);

            consolesDto.add(newConsoleDto);
        }

        return consolesDto;
    }

    public ConsoleDto updateConsole(Integer id, ConsoleDto consoleDto) throws ConsoleDtoNullException {
        if (ObjectUtils.isEmpty(consoleDto)) {
            throw new ConsoleDtoNullException();
        }

        Set<ConstraintViolation<ConsoleDto>> violations = validator.validate(consoleDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        consoleDto.setId(id);

        Console console = consoleMapper.consoleDtoToConsole(consoleDto);
        console.setPhotos(consolePhotoRepository.findByConsoleId(consoleDto.getId()));

        consoleRepository.save(console);

        ConsoleDto newConsoleDto = consoleMapper.consoleToConsoleDto(console);
        extractPhotoIdsForDto(console, newConsoleDto, consolePhotoRepository);

        return newConsoleDto;
    }

    public void deleteConsole(Integer id) throws ConsoleNotFoundException {
        Console console = consoleRepository.findById(id)
                .orElseThrow(ConsoleNotFoundException::new);

        consoleRepository.delete(console);
    }

    private void extractPhotoIdsForDto(Console console, ConsoleDto consoleDto, ConsolePhotoRepository consolePhotoRepository) {
        if (console.getPhotos() != null) {
            consoleDto.setPhotos(consolePhotoRepository.findIdsByConsoleId(console.getId()));
        } else {
            consoleDto.setPhotos(null);
        }
    }

}
