package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.exceptions.isnull.GameDtoNullException;
import it.shine.gamingverse.exceptions.listempty.GameListEmptyException;
import it.shine.gamingverse.exceptions.notfound.GameNotFoundException;
import it.shine.gamingverse.mappers.GameMapper;
import it.shine.gamingverse.repositories.GamePhotoRepository;
import it.shine.gamingverse.repositories.GameRepository;
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
    public GameDto addGame(GameDto gameDto) throws GameDtoNullException {
        if (ObjectUtils.isEmpty(gameDto)) {
            throw new GameDtoNullException();
        }

        Set<ConstraintViolation<GameDto>> violations = validator.validate(gameDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        Game game = gameMapper.gameDtoToGame(gameDto);
        game.setPhotos(gamePhotoRepository.findByGameId(gameDto.getId()));

        gameRepository.save(game);

        GameDto newGameDto = gameMapper.gameToGameDto(game);
        extractPhotoIdsForDto(game, newGameDto, gamePhotoRepository);

        return newGameDto;
    }

    public GameDto getGameById(Integer id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        GameDto newGameDto = gameMapper.gameToGameDto(game);
        extractPhotoIdsForDto(game, newGameDto, gamePhotoRepository);

        return newGameDto;
    }

    @Override
    public List<GameDto> getAllGames() throws GameListEmptyException {
        List<Game> games = gameRepository.findAll();

        if (games.isEmpty()) {
            throw new GameListEmptyException();
        }

        List<GameDto> gamesDto = new ArrayList<>();

        for (Game game : games) {
            GameDto newGameDto = gameMapper.gameToGameDto(game);
            extractPhotoIdsForDto(game, newGameDto, gamePhotoRepository);

            gamesDto.add(newGameDto);
        }

        return gamesDto;
    }

    @Override
    public GameDto updateGame(GameDto gameDto, Integer id) throws GameDtoNullException {
        if (ObjectUtils.isEmpty(gameDto)) {
            throw new GameDtoNullException();
        }

        Set<ConstraintViolation<GameDto>> violations = validator.validate(gameDto);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }

        gameDto.setId(id);

        Game game = gameMapper.gameDtoToGame(gameDto);
        game.setPhotos(gamePhotoRepository.findByGameId(gameDto.getId()));

        gameRepository.save(game);

        GameDto newGameDto = gameMapper.gameToGameDto(game);
        extractPhotoIdsForDto(game, newGameDto, gamePhotoRepository);

        return newGameDto;
    }

    @Override
    public void deleteGame(Integer id) throws GameNotFoundException {
        Game game = gameRepository.findById(id)
                .orElseThrow(GameNotFoundException::new);

        game.getPhotos().clear();

        gameRepository.delete(game);
    }

    private void extractPhotoIdsForDto(Game game,GameDto gameDto, GamePhotoRepository gamePhotoRepository) {
        if (game.getPhotos() != null) {
            gameDto.setPhotos(gamePhotoRepository.findIdsByGameId(game.getId()));
        } else {
            gameDto.setPhotos(null);
        }
    }

}