package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.ConsolePhotoDto;
import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.entities.ConsolePhoto;
import it.shine.gamingverse.mappers.utils.PhotoUtil;
import jakarta.persistence.EntityManager;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

import java.util.Base64;

@Mapper(componentModel = "spring")
public interface ConsolePhotoMapper {

    @Named("consolePhotoToConsolePhotoDto")
    default ConsolePhotoDto consolePhotoToConsolePhotoDto(ConsolePhoto consolePhoto) {
        ConsolePhotoDto consolePhotoDto = new ConsolePhotoDto();

        consolePhotoDto.setId(consolePhoto.getId());
        consolePhotoDto.setFilename(consolePhoto.getFilename());
        consolePhotoDto.setContent(Base64.getEncoder().encodeToString(consolePhoto.getContent()));
        consolePhotoDto.setConsole(consolePhoto.getConsole().getId());

        return consolePhotoDto;
    };

    @Named("consolePhotoDtoToConsolePhoto")
    default ConsolePhoto consolePhotoDtoToConsolePhoto(ConsolePhotoDto consolePhotoDto, EntityManager entityManager) {
        ConsolePhoto consolePhoto = new ConsolePhoto();

        consolePhoto.setId(consolePhotoDto.getId());
        consolePhoto.setFilename(consolePhotoDto.getFilename());

        PhotoUtil.setPhoto(consolePhotoDto, consolePhoto);

        Console console = entityManager.find(Console.class, consolePhotoDto.getConsole());
        consolePhoto.setConsole(console);

        return consolePhoto;
    };

}
