package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.GamePhotoDto;
import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.entities.GamePhoto;
import it.shine.gamingverse.mappers.utils.PhotoUtil;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface GamePhotoMapper {

    @Named("gamePhotoToGamePhotoDto")
    default GamePhotoDto gamePhotoToGamePhotoDto(GamePhoto gamePhoto) {
        GamePhotoDto gamePhotoDto = new GamePhotoDto();

        gamePhotoDto.setId(gamePhoto.getId());
        gamePhotoDto.setFilename(gamePhoto.getFilename());
        gamePhotoDto.setContent(Base64.getEncoder().encodeToString(gamePhoto.getContent()));
        gamePhotoDto.setGame(gamePhoto.getGame().getId());

        return gamePhotoDto;
    };

    @Named("gamePhotoDtoToGamePhoto")
    default GamePhoto gamePhotoDtoToGamePhoto(GamePhotoDto gamePhotoDto, EntityManager entityManager) {
        GamePhoto gamePhoto = new GamePhoto();

        gamePhoto.setId(gamePhotoDto.getId());
        gamePhoto.setFilename(gamePhotoDto.getFilename());

        PhotoUtil.setPhoto(gamePhotoDto, gamePhoto);

        Game game = entityManager.find(Game.class, gamePhotoDto.getGame());
        gamePhoto.setGame(game);

        return gamePhoto;
    };

}
