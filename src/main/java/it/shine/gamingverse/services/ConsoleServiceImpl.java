package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.exceptions.ConsoleNotFoundException;
import it.shine.gamingverse.mappers.ConsoleMapper;
import it.shine.gamingverse.repositories.ConsolePhotoRepository;
import it.shine.gamingverse.repositories.ConsoleRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public ConsoleDto addConsole(ConsoleDto consoleDto) {
        Set<ConstraintViolation<ConsoleDto>> violations = validator.validate(consoleDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Console console = consoleMapper.consoleDtoToConsole(consoleDto, consolePhotoRepository);

        consoleRepository.save(console);

        return consoleMapper.consoleToConsoleDto(console, consolePhotoRepository);
    }

    @Override
    public ConsoleDto getConsoleById(Integer id) throws Exception {
        Console console = consoleRepository.findById(id)
                .orElseThrow(ConsoleNotFoundException::new);
        return consoleMapper.consoleToConsoleDto(console, consolePhotoRepository);
    }

    public List<ConsoleDto> getAllConsoles() {
        List<ConsoleDto> consoles = new ArrayList<>();

        for (Console console : consoleRepository.findAll()) {
            consoles.add(consoleMapper.consoleToConsoleDto(console, consolePhotoRepository));
        }

        return consoles;
    }

    public ConsoleDto updateConsole(Integer id, ConsoleDto consoleDto) throws Exception {
        Console console = consoleRepository.findById(id)
                .orElseThrow(ConsoleNotFoundException::new);

        Set<ConstraintViolation<ConsoleDto>> violations = validator.validate(consoleDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        console.setConsoleName(consoleDto.getConsoleName());
        console.setPrice(consoleDto.getPrice());
        console.setDeveloper(consoleDto.getDeveloper());
        // TODO released
        console.setPhotos(consolePhotoRepository.findByConsoleId(consoleDto.getId()));

        consoleRepository.save(console);

        return consoleMapper.consoleToConsoleDto(console, consolePhotoRepository);
    }

    public void deleteConsole(Integer id) throws Exception {
        Console console = consoleRepository.findById(id)
                .orElseThrow(ConsoleNotFoundException::new);

        consoleRepository.delete(console);
    }

}
