package it.shine.gamingverse.services;

import it.shine.gamingverse.dtos.ConsoleDto;
import it.shine.gamingverse.exceptions.listempty.ConsoleListEmptyException;
import it.shine.gamingverse.exceptions.isnull.ConsoleDtoNullException;

import java.util.List;

public interface ConsoleService {

    ConsoleDto addConsole(ConsoleDto console) throws ConsoleDtoNullException;

    ConsoleDto getConsoleById(Integer id) throws Exception;

    List<ConsoleDto> getAllConsoles() throws ConsoleListEmptyException;

    ConsoleDto updateConsole(Integer id, ConsoleDto console) throws Exception;

    void deleteConsole(Integer id) throws Exception;

}
