package it.shine.gamingverse.repositories;

import it.shine.gamingverse.entities.ConsolePhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConsolePhotoRepository extends JpaRepository<ConsolePhoto, Integer> {

    List<ConsolePhoto> findByConsoleId(Integer consoleId);

    @Query("SELECT cp.id FROM ConsolePhoto cp WHERE cp.console.id = :consoleId")
    List<Integer> findIdsByConsoleId(@Param("consoleId") Integer consoleId);

}
