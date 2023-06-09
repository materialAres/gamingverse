package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.exceptions.GameNotFoundException;
import it.shine.gamingverse.mappers.GameMapper;
import it.shine.gamingverse.repositories.GamePhotoRepository;
import it.shine.gamingverse.repositories.GameRepository;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class GameServiceImpl implements GameService {

    @Autowired
    private GameRepository gameRepository;

    @Autowired
    private GamePhotoRepository gamePhotoRepository;

    @Autowired
    private GameMapper gameMapper;

    @Autowired
    private Validator validator;

    @Override
    public GameDto addGame(GameDto gameDto) {
        Set<ConstraintViolation<GameDto>> violations = validator.validate(gameDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Game game = gameMapper.gameDtoToGame(gameDto, gamePhotoRepository);

        gameRepository.save(game);

        return gameMapper.gameToGameDto(game, gamePhotoRepository);
    }

    public GameDto getGameById(Integer id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        return gameMapper.gameToGameDto(game, gamePhotoRepository);
    }

    public List<GameDto> getAllGames() {
        List<GameDto> games = new ArrayList<>();

        for (Game game : gameRepository.findAll()) {
            games.add(gameMapper.gameToGameDto(game, gamePhotoRepository));
        }

        return games;
    }

    public GameDto updateGame(Integer id, GameDto gameDto) throws GameNotFoundException {
        Set<ConstraintViolation<GameDto>> violations = validator.validate(gameDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        game.setTitle(gameDto.getTitle());
        game.setGenre(gameDto.getGenre());
        game.setPrice(gameDto.getPrice());
        game.setDeveloper(gameDto.getDeveloper());
        game.setPublisher(gameDto.getPublisher());
        game.setReleased(gameDto.getReleased());
        game.setPhotos(gamePhotoRepository.findByGameId(gameDto.getId()));
        game.setConsole(gameDto.getConsole());

        gameRepository.save(game);

        return gameMapper.gameToGameDto(game, gamePhotoRepository);
    }

    public void deleteGame(Integer id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        game.getPhotos().clear();

        gameRepository.delete(game);
    }

}


