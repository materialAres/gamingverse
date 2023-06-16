package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.repositories.GamePhotoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Named("gameToGameDto")
    default GameDto gameToGameDto(Game game, GamePhotoRepository gamePhotoRepository) {
        GameDto gameDto = new GameDto(game);

        if (game.getPhotos() != null) {
            gameDto.setPhotos(gamePhotoRepository.findIdsByGameId(game.getId()));
        } else {
            gameDto.setPhotos(null);
        }

        return gameDto;
    };

    @Named("gameDtoToGame")
    default Game gameDtoToGame(GameDto gameDto, GamePhotoRepository gamePhotoRepository) {
        Game game = new Game();

        game.setId(gameDto.getId());
        game.setTitle(gameDto.getTitle());
        game.setGenre(gameDto.getGenre());
        game.setPrice(gameDto.getPrice());
        game.setDeveloper(gameDto.getDeveloper());
        game.setPublisher(gameDto.getPublisher());
        game.setConsole(gameDto.getConsole());
        game.setReleased(gameDto.getReleased());
        game.setCreatedAt(gameDto.getCreatedAt());
        game.setUpdatedAt(gameDto.getUpdatedAt());
        game.setPhotos(gamePhotoRepository.findByGameId(gameDto.getId()));

        return game;
    };

}
