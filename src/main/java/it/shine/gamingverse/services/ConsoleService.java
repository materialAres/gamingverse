package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsoleDto;

import java.util.List;

public interface ConsoleService {

    ConsoleDto addConsole(ConsoleDto console);

    ConsoleDto getConsoleById(Integer id) throws Exception;

    List<ConsoleDto> getAllConsoles();

    ConsoleDto updateConsole(Integer id, ConsoleDto console) throws Exception;

    void deleteConsole(Integer id) throws Exception;

}
