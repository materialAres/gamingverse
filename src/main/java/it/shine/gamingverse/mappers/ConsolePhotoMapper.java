package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.entities.ConsolePhoto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsolePhotoMapper {

    @Mapping(target = "content", ignore = true)
    @Mapping(target = "consoleId", source = "console.id")
    ConsolePhotoDto consolePhotoToConsolePhotoDto(ConsolePhoto consolePhoto);

    @Mapping(target = "content", ignore = true)
    @Mapping(target = "console.id", source = "consoleId")
    ConsolePhoto consolePhotoDtoToConsolePhoto(ConsolePhotoDto consolePhotoDto);

}
