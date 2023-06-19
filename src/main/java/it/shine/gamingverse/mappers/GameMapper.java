package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.GameDto;
import it.shine.gamingverse.dtos.OrderDto;
import it.shine.gamingverse.entities.Game;
import it.shine.gamingverse.entities.Order;
import it.shine.gamingverse.repositories.GamePhotoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface GameMapper {

    @Mapping(target = "photos", ignore = true)
    GameDto gameToGameDto(Game game);

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Game gameDtoToGame(GameDto gameDto);

}
