package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.entities.Console;
import it.shine.gamingverse.repositories.ConsolePhotoRepository;
import org.mapstruct.Mapper;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ConsoleMapper {

    @Named("consoleToConsoleDto")
    default ConsoleDto consoleToConsoleDto(Console console, ConsolePhotoRepository consolePhotoRepository) {
        ConsoleDto consoleDto = new ConsoleDto(console);

        if (console.getPhotos() != null) {
            consoleDto.setPhotos(consolePhotoRepository.findIdsByConsoleId(console.getId()));
        } else {
            consoleDto.setPhotos(null);
        }

        return consoleDto;
    };

    @Named("consoleDtoToConsole")
    default Console consoleDtoToConsole(ConsoleDto consoleDto, ConsolePhotoRepository consolePhotoRepository) {
        Console console = new Console();

        console.setId(consoleDto.getId());
        console.setConsoleName(consoleDto.getConsoleName());
        console.setDeveloper(consoleDto.getDeveloper());
        console.setPrice(consoleDto.getPrice());
        console.setReleased(consoleDto.getReleased());
        console.setCreatedAt(consoleDto.getCreatedAt());
        console.setUpdatedAt(consoleDto.getUpdatedAt());
        // TODO released
        console.setPhotos(consolePhotoRepository.findByConsoleId(consoleDto.getId()));

        return console;
    };
}
