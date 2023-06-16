package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.dtos.GamePhotoDto;
import it.shine.gamingverse.entities.GamePhoto;
import it.shine.gamingverse.exceptions.listempty.GamePhotoListEmptyException;
import it.shine.gamingverse.exceptions.isnull.GamePhotoDtoNullException;
import it.shine.gamingverse.exceptions.notfound.GamePhotoNotFoundException;
import it.shine.gamingverse.mappers.GamePhotoMapper;
import it.shine.gamingverse.repositories.GamePhotoRepository;
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
public class GamePhotoServiceImpl implements GamePhotoService {

    @Autowired
    private GamePhotoRepository gamePhotoRepository;

    @Autowired
    private GamePhotoMapper gamePhotoMapper;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private Validator validator;

    @Override
    public GamePhotoDto addGamePhoto(GamePhotoDto gamePhotoDto) throws GamePhotoDtoNullException {
        if (ObjectUtils.isEmpty(gamePhotoDto)) {
            throw new GamePhotoDtoNullException();
        }

        GamePhoto gamePhoto = gamePhotoMapper.gamePhotoDtoToGamePhoto(gamePhotoDto, entityManager);

        Set<ConstraintViolation<GamePhotoDto>> violations = validator.validate(gamePhotoDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        gamePhotoRepository.save(gamePhoto);

        return gamePhotoDto;
    }

    @Override
    public GamePhotoDto getGamePhotoById(Integer id) throws GamePhotoNotFoundException {
        GamePhoto gamePhoto = gamePhotoRepository.findById(id)
                .orElseThrow(GamePhotoNotFoundException::new);

        return gamePhotoMapper.gamePhotoToGamePhotoDto(gamePhoto);
    }

    @Override
    public List<GamePhotoDto> getAllGamePhotos() throws GamePhotoListEmptyException {
        List<GamePhoto> gamePhotos = gamePhotoRepository.findAll();

        if (gamePhotos.isEmpty()) {
            throw new GamePhotoListEmptyException();
        }

        return gamePhotos
                .stream()
                .map(gamePhotoMapper::gamePhotoToGamePhotoDto)
                .collect(Collectors.toList());
    }

    @Override
    public GamePhotoDto updateGamePhoto(GamePhotoDto gamePhotoDto) throws GamePhotoDtoNullException {
        if (ObjectUtils.isEmpty(gamePhotoDto)) {
            throw new GamePhotoDtoNullException();
        }

        Set<ConstraintViolation<GamePhotoDto>> violations = validator.validate(gamePhotoDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        GamePhoto gamePhoto = gamePhotoMapper.gamePhotoDtoToGamePhoto(gamePhotoDto, entityManager);

        gamePhotoRepository.save(gamePhoto);

        return gamePhotoMapper.gamePhotoToGamePhotoDto(gamePhoto);
    }

    @Override
    public void deleteGamePhoto(Integer id) throws GamePhotoNotFoundException {
        if (!gamePhotoRepository.existsById(id)) {
            throw new GamePhotoNotFoundException();
        }

        gamePhotoRepository.deleteById(id);
    }

}

