package it.shine.gamingverse.mappers;

import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.entities.Console;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ConsoleMapper {

    @Mapping(target = "photos", ignore = true)
    ConsoleDto consoleToConsoleDto(Console console);

    @Mapping(target = "seller", ignore = true)
    @Mapping(target = "photos", ignore = true)
    Console consoleDtoToConsole(ConsoleDto consoleDto);

}
